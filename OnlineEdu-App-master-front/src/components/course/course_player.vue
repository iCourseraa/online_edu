<template>
  <el-row style="position: relative" class="course">
    <el-col :span="17" style="position: relative">
      <div style="padding-top: 56.25%" />
      <div class="player">
        <div class="preplay">
          <div class="fade" />
          <i class="el-icon-video-play trnfcenter play-btn" @click="playFirst" />
          <el-image fit="cover" :src="encodeOssFileUri(course.cover)" class="img" />
        </div>
        <div id="J_prismPlayer" @dblclick="requestFullScreen" @contextmenu.prevent="requestPlay" />
      </div>
    </el-col>
    <el-col :key="refreshKey" :span="7" class="selection">
      <div v-for="chapter of chapterData" :key="chapter.id" class="chapter">
        <div class="title ellipse" :title="chapter.title">{{ chapter.title }}</div>
        <div
          v-for="value of videoData[chapter.id]"
          :key="`${chapter.id}:${value.id}`"
          :class="{'video': true, 'active': activeItem === `${chapter.id}:${value.id}`}"
          @click="clickChapterItem(value, `${chapter.id}:${value.id}`)"
        >
          <div class="title ellipse" :title="value.title">
            <i class="el-icon-video-play" />
            {{ value.title }}
          </div>
          <div class="info">
            <span class="duration">时长: {{ value.duration }}</span>
            <span v-if="course.price > 0 && value.free" class="free">免费</span>
          </div>
        </div>
      </div>
    </el-col>
  </el-row>
</template>

<script>
import { getChapters, getChapterVideos, getVideoPlayAuth } from '@/api/content'
import { mapGetters } from 'vuex'
import { encodeOssFileUri, jsonObj2FormData } from '@/utils'
import { saveVideoNumber } from '@/api/videoNumber'

export default {
  name: 'EducourseWatch',
  data() {
    return {
      course: { cover: '', price: 0 },
      player: null,
      chapterData: [],
      videoId: '',
      videoData: {},
      refreshKey: false,
      activeItem: '',
      dataNumber: {
        videoId: '',
        userId: '',
        playNumber: 0, // 开始次数
        pauseNumber: 0, // 暂停次数
        endedNumber: 0, // 播放完成次数
        seekedNumber: 0, // 拖拽次数
        completionRateNumber: 0, // 播放完成率
        currTime: 0, // 当前播放时长
        durationNumber: 0, // 视频总时长
        kjNumber: 0, // 快进次数
        ktNumber: 0, // 快退次数
      },
      saveInterval: null, // 定时器ID
      intervalSet: false, // 是否已设置定时器
      currentVideoDuration: 0, // 当前视频时长
    }
  },
  computed: {
    ...mapGetters(['user'])
  },
  created() {
    this.$notify.info({
      title: '提示',
      dangerouslyUseHTMLString: true,
      message: '鼠标左键双击播放器进入或退出全屏<br>鼠标右键单击播放器切换暂停与播放',
      offset: 50,
      duration: 3600
    })
    // 在组件创建时获取初始数据
    this.fetchVideoList()
    this.fetchUserDetailList()
  },
  mounted() {
    window.addEventListener('hashchange', this.handleHashChange);
    // 初始化不设置定时器，等待视频加载完成后动态设置
  },
  beforeDestroy() {
    window.removeEventListener('hashchange', this.handleHashChange);
    this.clearSaveInterval();
    // 组件销毁前发送剩余数据
    this.saveVideo();
  },
  methods: {
    setData(course) {
      this.course = course
      this.listChaptersAndVideos(course.id)
    },
    encodeOssFileUri(ossUri) {
      return encodeOssFileUri(ossUri)
    },
    listChaptersAndVideos(courseId) {
      getChapters(courseId).then(resp => {
        this.chapterData = resp.data
        let len = resp.data.length
        for (const c of resp.data) {
          getChapterVideos(c.id).then(resp => {
            this.videoData[c.id] = resp.data
            // 获取完视频列表
            if (--len <= 0) {
              // 关键：强制刷新组件渲染
              this.refreshKey = !this.refreshKey
            }
          })
        }
      })
    },
    clickChapterItem(video, activeItem) {
      if (this.user === null || Object.keys(this.user).length === 0) {
        // this.$message.info('请登录后再操作')
        this.$login()
        return
      }
      this.activeItem = activeItem
      this.play(video)
    },
    play(video) {
      if(this.videoId !== '' && this.videoId !== video.id){
        this.saveVideo()
      }
      // 重置统计计数，保留 videoId 和 userId
      this.resetDataNumber()
      this.dataNumber.videoId = video.id
      this.dataNumber.userId = this.user.id || ''
      this.videoId = video.id
      console.log("当前视频ID:", this.videoId)
      const params = { courseId: video.courseId, videoId: video.id, videoSourceId: video.videoId }
      if (this.player) {
        // 销毁播放器
        this.player.dispose()
        this.player = null
        document.getElementById('J_prismPlayer').innerHTML = ''
        this.initPlayer(params)
      } else {
        const p = document.querySelector('.player .preplay')
        if (p) p.parentNode.removeChild(p)
        this.initPlayer(params)
      }
    },
    playFirst() {
      if (this.user === null || Object.keys(this.user).length === 0) {
        // this.$message.info('请登录后再操作')
        this.$login()
        return
      }
      // 准备第一个视频
      try {
        const firstChapterId = this.chapterData[0].id
        const firstVideo = this.videoData[firstChapterId][0]
        this.play(firstVideo)
        // 延迟一秒开始播放
        setTimeout(() => {
          if(this.player) this.player.play()
        }, 1000)
        // 设置第一个视频为激活状态
        this.activeItem = `${firstChapterId}:${firstVideo.id}`
      } catch (e) {
        console.log(e)
      }
    },
    initPlayer(params = { courseId: 0, videoId: 0, videoSourceId: '' }) {
      getVideoPlayAuth(jsonObj2FormData(params))
        .then(resp => {
          // 初始化播放器
          this.player = new Aliplayer({
            id: 'J_prismPlayer',
            autoplay: false,
            width: '100%',
            height: '100%',
            vid: params.videoSourceId,
            playauth: resp.data
          })
          this.bindPlayerEvents()
        })
        .catch(err => {
          console.log(err)
        })
    },
    requestFullScreen() { // 进入/退出全屏
      if (this.player) {
        if (this.player.fullscreenService.getIsFullScreen()) {
          this.player.fullscreenService.cancelFullScreen()
        } else {
          this.player.fullscreenService.requestFullScreen()
        }
      }
    },
    requestPlay() { // 暂停/播放
      if (this.player) {
        const status = this.player.getStatus()
        if (status === 'playing') {
          this.player.pause()
        } else {
          this.player.play()
        }
      }
    },
    bindPlayerEvents() {
      if (!this.player) return;

      // 初始化播放时间记录
      let lastPlayTime = 0;

      // 播放事件
      this.player.on('play', () => {
        console.log('播放开始');
        this.dataNumber.playNumber += 1;
        // 获取当前播放时间
        this.dataNumber.currTime = Math.round(this.player.getCurrentTime());
        // 不再立即发送数据
      });

      // 暂停事件
      this.player.on('pause', () => {
        console.log('播放暂停');
        this.dataNumber.pauseNumber += 1;
        // 获取当前播放时间
        this.dataNumber.currTime = Math.round(this.player.getCurrentTime());
        // 不再立即发送数据
      });

      // 播放完成事件
      this.player.on('ended', () => {
        console.log('播放完成');
        this.dataNumber.endedNumber = 1;
        // 获取当前播放时间
        this.dataNumber.currTime = Math.round(this.player.getCurrentTime());
        // 不再立即发送数据
      });

      // 视频拖拽完成事件
      this.player.on('seeked', () => {
        console.log('拖拽完成');
        this.dataNumber.seekedNumber += 1;
        // 获取当前播放时间
        this.dataNumber.currTime = Math.round(this.player.getCurrentTime());
        // 不再立即发送数据
      });

      // 时间更新事件（用于实时计算播放进度）
      this.player.on('timeupdate', () => {
        const currentTime = this.player.getCurrentTime();
        lastPlayTime = currentTime;
        const duration = this.player.getDuration();
        console.log(`当前播放时间: ${currentTime}, 总时长: ${duration}`);
        
        // 计算播放完成率
        const completionRate = (currentTime / duration) * 100;
        this.dataNumber.completionRateNumber = Math.round(completionRate);
        this.dataNumber.currTime = Math.round(currentTime);
        this.dataNumber.durationNumber = Math.round(duration);
        console.log(`播放完成率: ${completionRate}%`);

        // 设置定时器为每2秒发送一次数据
        if (!this.intervalSet && duration > 0) {
          this.setSaveInterval(2); // 固定为2秒
          this.intervalSet = true;
        }
      });

      // 监听 seeking 事件
      this.player.on('seeking', () => {
        const currentTime = this.player.getCurrentTime();
        if (currentTime > lastPlayTime) {
          this.dataNumber.kjNumber += 1;
          console.log(`用户快进至: ${currentTime} 秒`);
        } else {
          this.dataNumber.ktNumber += 1;
          console.log(`用户快退至: ${currentTime} 秒`);
        }
        // 更新最后播放时间
        lastPlayTime = currentTime;
        this.dataNumber.currTime = Math.round(currentTime);
        // 不再立即发送数据
      });

      // 监听播放速度变化事件
      this.player.on('rate', () => {
        console.log("this.playerthis.player", this.player)
        const playbackRate = this.player.getPlaybackRate();
        console.log(`播放速度改变: ${playbackRate}x`);
      });

      // 如果有其他事件需要监听，可以在这里添加
    },
    handleHashChange() {
      // 自定义返回逻辑，例如弹出提示框或保存数据
      if (confirm('确定要退出当前视频吗？')) {
        this.saveVideo()
        return true; // 允许返回
      } else {
        // 阻止返回，需要使用路由守卫
      }
    },
    setSaveInterval(intervalInSeconds = 2) {
      // 清除已有的定时器
      this.clearSaveInterval();
      // 设置新的定时器
      this.saveInterval = setInterval(this.saveVideo, intervalInSeconds * 1000);
      console.log(`设置数据发送间隔为每 ${intervalInSeconds} 秒`);
    },
    clearSaveInterval() {
      if (this.saveInterval) {
        clearInterval(this.saveInterval);
        this.saveInterval = null;
        this.intervalSet = false;
        console.log("清除了数据发送定时器");
      }
    },
    saveVideo(){
      if(this.dataNumber.videoId ){
        // 检查是否有数据变化（除了 currTime 和 completionRateNumber）
        const { playNumber, pauseNumber, endedNumber, seekedNumber, kjNumber, ktNumber } = this.dataNumber;
        if (
          playNumber === 0 &&
          pauseNumber === 0 &&
          endedNumber === 0 &&
          seekedNumber === 0 &&
          kjNumber === 0 &&
          ktNumber === 0
        ) {
          // 除了 currTime 和 completionRateNumber 外，没有其他数据变化，跳过保存
          console.log("没有数据变化，跳过 saveVideo()");
          return;
        }

        // 确保 userId 已经设置
        this.dataNumber.userId = this.user.id || ''

        // 确保 currTime 和 completionRateNumber 被四舍五入
        this.dataNumber.currTime = Math.round(this.dataNumber.currTime)
        this.dataNumber.completionRateNumber = Math.round(this.dataNumber.completionRateNumber)
        this.dataNumber.durationNumber = Math.round(this.dataNumber.durationNumber)

        // 发送数据到后端
        saveVideoNumber(this.dataNumber).then(res=>{
          console.log("保存成功", res)
          // 重置统计计数，保留 videoId 和 userId
          this.resetDataNumber()
        }).catch(err => {
          console.error("保存失败", err)
          // 可选：实现重试逻辑
        })
      }
    },
    resetDataNumber() {
      this.dataNumber.playNumber = 0
      this.dataNumber.pauseNumber = 0
      this.dataNumber.endedNumber = 0
      this.dataNumber.seekedNumber = 0
      this.dataNumber.completionRateNumber = 0
      this.dataNumber.currTime = 0
      this.dataNumber.durationNumber = 0
      this.dataNumber.kjNumber = 0
      this.dataNumber.ktNumber = 0
      // videoId 和 userId 保留
    }
  // beforeRouteLeave(to, from, next) {
  //   if (this.handleExitAction()) {
  //     next(); // 放行返回
  //   } else {
  //     next(false); // 阻止返回
  //   }
  },
}
</script>

<style scoped lang="scss">

.player {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: #333;

  .preplay {
    height: 100%;
    position: relative;

    .fade {
      height: 100%;
      width: 100%;
      background-color: rgba(0, 0, 0, .3);
      position: absolute;
      top: 0;
      left: 0;
      z-index: 1;
    }

    .img {
      width: 100%;
      height: 100%;
    }

    .play-btn {
      font-size: 76px;
      z-index: 2;
      color: #23b8ff;
      cursor: pointer;

      &:hover {
        color: #409eff;
      }
    }
  }
}

.course {
  .selection {
    position: absolute;
    right: 0;
    height: 100%;
    padding: 20px 16px;
    overflow: auto;
    background-color: #232323;

    &::-webkit-scrollbar {
      width: 4px;
    }

    &::-webkit-scrollbar-thumb {
      background: #64a5ff;
    }

    &::-webkit-scrollbar-track {
      background: #232323;
    }
  }
}

.chapter {
  color: #c7c7c7;
  margin-top: 16px;

  > .title {
    font-size: 16px;
  }

  .video {
    font-size: 14px;
    margin-top: 16px;
    cursor: pointer;

    i {
      margin-right: 6px;
    }

    &:hover, &.active {
      color: #20a0ff;

      .duration {
        color: #20a0ff;
      }
    }

    .info {
      font-size: 12px;
      margin: 5px 0 0 20px;
      color: #777777;

      span {
        margin-right: 12px;

        &:last-child {
          margin-right: 0;
        }
      }

      .free {
        color: #ff4f23;
      }
    }
  }
}
</style>


