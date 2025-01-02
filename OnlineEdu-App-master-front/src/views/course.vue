<template>
  <el-row type="flex" justify="center">
    <el-col :span="20">
      <!-- 面包屑 -->
      <el-breadcrumb separator-class="el-icon-arrow-right" style="margin: 20px 0;color: #333">
        <el-breadcrumb-item>全部课程</el-breadcrumb-item>
        <el-breadcrumb-item v-for="(item, index) in getDetailsSubject(course.subjectParent)" :key="index">
          {{ item }}
        </el-breadcrumb-item>
        <el-breadcrumb-item>{{ course.title }}</el-breadcrumb-item>
      </el-breadcrumb>
      <!-- 播放器 -->
      <div style="position: relative;width: 100%">
        <v-course-player ref="CoursePlayer" />
        <!-- 收藏课程按钮 -->
      </div>

      <div class="sub-course">
        <div>
          <div style="color: #fff;font-size: 20px;">
            {{ course.title }}
          </div>
          <div style="color: #999;font-size: 13px;margin-top: 5px">
            <span style="margin-right: 26px">{{ course.lessonNum }} 总课时</span>
            <span style="margin-right: 26px">{{ course.buyCount }} 人收藏过</span>
            <!--              <span>{{ course.viewCount }} 人观看过</span>-->
          </div>
        </div>
        <div style="margin-left: auto">
          <div style="width: 22vw;text-align: center;padding-bottom: 10px;">
            <el-button
              type="primary"
              style="width: 18vw;font-size: 18px"
              icon="el-icon-star-on"
              @click="addToFavorites"
            >
              {{ isFavorited ? '取消收藏' : '收藏课程' }}
            </el-button>
          </div>

          <!-- <div style="margin-left: auto"> -->
          <!-- <div style="width: 22vw;text-align: center">
            <el-button
            type="primary"
            style="width: 18vw;font-size: 18px"
            icon="el-icon-star-on"
            @click="addToFavorites"
          >
            加入学习
          </el-button>
          </div> -->

          <!-- </div> -->
        </div>
      </div>

      <!-- 课程简介、评价 -->
      <div class="clearfix" style="margin-top: 20px">
        <div style="width: 73%;background-color: #fff;padding: 10px 20px;float: left;">
          <el-tabs>
            <el-tab-pane label="课程概述" class="course-descriptiont" v-html="course.description" />
            <el-tab-pane label="课程评价" lazy>
              <v-course-comment :course="course" />
            </el-tab-pane>
          </el-tabs>
        </div>
        <!-- 讲师简介 -->
        <div style="width: 26%;background-color: #fff;float: right">
          <el-card>
            <div slot="header">讲师简介</div>
            <div style="display: flex;align-items: center;">
              <router-link :to="{name: 'SearchByTeacher', params: { teacher: teacher.id || 0 }}">
                <el-avatar fit="contain" :src="teacher.avatar" :size="70" style="float: left;margin-right: 12px" />
              </router-link>
              <div>
                <div style="color: #20a0ff;font-size: 18px;cursor: pointer" @click="linkToTeacher(teacher.id)">
                  {{ teacher.name }}
                </div>
                <div style="margin-top: 10px;font-size: 14px" :title="teacher.email">
                  <i class="el-icon-message" />
                  {{ teacher.email }}
                </div>
              </div>
            </div>
            <div style="margin-top: 12px;font-size: 15px;line-height: 24px;color: #666">
              {{ teacher.intro }}
            </div>
          </el-card>
        </div>
      </div>
    </el-col>
  </el-row>
</template>

<script>
import { getCourseDetail, getTeacher, getIsBuyCourse, getIsStudy, getIsfavoriteCourse} from '@/api/content'
import MvCountDown from 'mv-count-down'
import { mapGetters } from 'vuex'

export default {
  name: 'Course',
  components: {
    'v-course-player': () => import('@/components/course/course_player'),
    'v-course-comment': () => import('@/components/course/course_comment'),
    MvCountDown
  },
  data() {
    return {
      course: {},
      teacher: {},
      buyCourseDialogVisible: false,
      orderNo: '',
      isBuyTheCourse: false,
      isFavorited: false
    }
  },
  computed: {
    ...mapGetters(['user'])
  },
  created() {
    const courseId = this.$route.params.id
    this.getCourseData(courseId)
    this.checkFavoriteStatus(courseId); // 检查收藏状态
 //   this.getIsBuyCourse(courseId)
    getIsStudy(courseId)
  },
  methods: {
    checkFavoriteStatus(courseId) {
      getIsfavoriteCourse(courseId).then(resp => {
        if (resp.message === '已收藏') {
          this.isFavorited = true;
        } else if (resp.message === '未收藏') {
          this.isFavorited = false;
        } else {
          console.error('未知的收藏状态:', resp.message);
        }
      }).catch(error => {
        console.error('获取收藏状态失败:', error);
      });
    },

    getCourseData(id) {
      getCourseDetail(id).then(resp => {
        this.course = resp.data
        // 获取讲师信息
        this.getTeacher(this.course.teacherId)
        // 初始化播放器
        setTimeout(() => {
          this.$refs.CoursePlayer.setData(this.course)
        }, 100)
      })
    },
    getTeacher(id) {
      getTeacher(id).then(resp => {
        this.teacher = resp.data
      })
    },
    getIsBuyCourse(id) {
      if (this.user === null || Object.keys(this.user).length === 0) {
        return
      }
      getIsBuyCourse(id).then(resp => {
        this.isBuyTheCourse = resp.data
      })
    },
    getDetailsSubject(subjectParent) {
      let subject = []
      let parent = subjectParent
      while (parent) {
        subject = [parent.title, ...subject]
        parent = parent.parent
      }
      return subject
    },
    addToFavorites() {
      if (!this.user || !this.course.id) {
        this.$message.error('无法收藏课程，请登录后重试');
        return;
      }

      // 检查是否已经收藏过该课程
      getIsBuyCourse(this.course.id).then(response => {
          console.log(response)
          const favoriteData = {
            userId: this.user.id,
            courseId: this.course.id,
          };
        // this.$message.success('收藏成功');
        if (response.message === "收藏成功") {
        this.$message.success("收藏成功");
        this.isFavorited = true; 
      } else if (response.message === "取消收藏成功") {
        this.$message.success("取消收藏成功");
        this.isFavorited = false; 
      } else {
      }
      }).catch(error => {

      });
    },
    addToFavorites1() {
      if (!this.user || !this.course.id) {
        this.$message.error('无法收藏课程，请登录后重试');
        return;
      }

      // 检查是否已经收藏过该课程
      getIsStudy(this.course.id).then(response => {

          const favoriteData = {
            userId: this.user.id,
            courseId: this.course.id,
          };

      }).catch(error => {

      });
    },
    linkToTeacher(teacherId) {
      this.$router.push({ name: 'SearchByTeacher', params: { teacher: teacherId } })
    }
  }
}
</script>

<style lang="scss" scoped>
.sub-course {
  background-color: #333;
  //position: absolute;
  bottom: 0;
  width: 100%;
  min-height: 80px;
  z-index: 3;
  padding: 14px 16px;
  display: flex;
  align-items: center;
}

// 课程介绍颜色
.course-descriptiont {
  /* table 样式 */
  table {
    border-top: 1px solid #ccc;
    border-left: 1px solid #ccc;
  }

  table td,
  table th {
    border-bottom: 1px solid #ccc;
    border-right: 1px solid #ccc;
    padding: 3px 5px;
  }

  table th {
    border-bottom: 2px solid #ccc;
    text-align: center;
  }

  /* blockquote 样式 */
  blockquote {
    display: block;
    border-left: 8px solid #d0e5f2;
    padding: 5px 10px;
    margin: 10px 0;
    line-height: 1.4;
    font-size: 100%;
    background-color: #f1f1f1;
  }

  /* code 样式 */
  code {
    display: inline-block;
    *display: inline;
    *zoom: 1;
    background-color: #f1f1f1;
    border-radius: 3px;
    padding: 3px 5px;
    margin: 0 3px;
  }

  pre code {
    display: block;
  }

  /* ul ol 样式 */
  ul, ol {
    margin: 10px 0 10px 20px;
  }
}
</style>
