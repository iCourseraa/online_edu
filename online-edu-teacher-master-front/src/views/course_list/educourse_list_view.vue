<template>
  <!-- 列表视图 -->
  <div class="list-view">
    <h3>视频列表</h3>
    <el-table :data="videoList" border style="width: 100%">
      <el-table-column prop="title" label="视频标题" />
      <el-table-column prop="duration" label="视频时长" />
      <el-table-column label="操作">
        <template #default="scope">
          <el-button size="small" @click="selectVideo(scope.row)">
            查看详情
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 视频详情弹窗 -->
    <el-dialog title="视频观看详情" :visible.sync="show" width="80%">
      <el-table :data="videoDetailList">
        <el-table-column property="nickname" label="昵称" width="150"></el-table-column>
        <el-table-column property="videoTitle" label="视频标题" width="150"></el-table-column>
        <el-table-column property="playNumber" label="点击播放按钮次数" width="200"></el-table-column>
        <el-table-column property="pauseNumber" label="点击暂停按钮次数"></el-table-column>
        <el-table-column property="endedNumber" label="是否完整播放"></el-table-column>
        <el-table-column property="seekNumber" label="拖拽次数"></el-table-column>
        <el-table-column property="completionRateNumber" label="完成率"></el-table-column>
        <el-table-column property="currTime" label="播放时长"></el-table-column>
        <el-table-column property="kjNumber" label="快进次数"></el-table-column>
        <el-table-column property="ktNumber" label="快退次数"></el-table-column>
      </el-table>
      <!-- 显示视频质量 -->
      <div class="video-quality">
        <strong>视频质量：</strong>{{ videoQuality }}
      </div>
    </el-dialog>

    <!-- 统计图 -->
    <div>统计图</div>
    <div id="myChart" ref="myChart" style="width: 100%; height: 480px;"></div>
    <!-- 显示视频质量 -->
    <div class="video-quality-summary">
      <strong>视频质量：</strong>{{ videoQuality }}
    </div>
  </div>
</template>

<script>
import { listAll, videoNumber } from '@/api/video'; // 确保这些API方法存在
import * as echarts from "echarts";

export default {
  name: "EduCourseListView",
  data() {
    return {
      videoList: [], // 视频列表数据
      videoDetailList: [], // 当前选中的视频详情数据
      show: false, // 控制弹窗显示
      option: {
        tooltip: {
          trigger: "axis",
        },
        legend: {
          data: [
            "点击播放",
            "点击暂停",
            "快进次数",
            "快退次数",
          ],
        },
        xAxis: {
          type: "category",
          boundaryGap: false,
          data: [], // 动态设置
        },
        yAxis: {
          type: "value",
        },
        dataZoom: [
          {
            type: "slider",
            show: true,
            start: 0,
            end: 100,
            bottom: 20, // 滑块位置
          },
        ],
        series: [],
      },
      maxDuration: 0, // 当前视频的最大播放时长
      videoQuality: 0, // 视频质量
    };
  },
  mounted() {
    this.fetchVideoList();
    this.fetchVideoDetailView();
    // 监听窗口大小变化以自适应图表
    window.addEventListener("resize", this.handleResize);
  },
  beforeDestroy() {
    window.removeEventListener("resize", this.handleResize);
  },
  watch: {
    videoDetailList() {
      // 选中视频后，更新图表和视频质量
      this.getChart();
      this.calculateVideoQuality();
    },
  },
  methods: {
    // 处理窗口大小变化
    handleResize() {
      const myChart = echarts.getInstanceByDom(this.$refs.myChart);
      if (myChart) {
        myChart.resize();
      }
    },

    // 处理和聚合数据
    processData() {
      if (!this.videoDetailList.length) {
        // 没有数据时清空图表
        this.option.xAxis.data = [];
        this.option.series = [];
        return;
      }

      // 计算视频的总时长
      this.maxDuration = Math.max(
        ...this.videoDetailList.map((item) => item.currTime)
      );

      // 计算发送数据的间隔时间（秒）
      const interval = Math.max(Math.floor(this.maxDuration / 20), 1); // 最小为1秒

      // 创建时间段
      const segments = [];
      for (let i = 0; i <= this.maxDuration; i += interval) {
        segments.push(Math.round(i));
      }

      // 初始化数据数组
      const playNumberData = Array(segments.length - 1).fill(0);
      const pauseNumberData = Array(segments.length - 1).fill(0);
      const kjNumberData = Array(segments.length - 1).fill(0);
      const ktNumberData = Array(segments.length - 1).fill(0);

      // 聚合数据到各个时间段
      this.videoDetailList.forEach((item) => {
        const segmentIndex = Math.min(
          Math.floor(item.currTime / interval),
          segments.length - 2
        );
        playNumberData[segmentIndex] += item.playNumber;
        pauseNumberData[segmentIndex] += item.pauseNumber;
        kjNumberData[segmentIndex] += item.kjNumber;
        ktNumberData[segmentIndex] += item.ktNumber;
      });

      // 设置X轴的时间段标签，例如"0s - 5s"
      const xAxisData = [];
      for (let i = 0; i < segments.length - 1; i++) {
        xAxisData.push(`${segments[i]}s - ${segments[i + 1]}s`);
      }

      this.option.xAxis.data = xAxisData;

      // 更新系列数据
      this.option.series = [
        { name: "点击播放", type: "line", data: playNumberData },
        { name: "点击暂停", type: "line", data: pauseNumberData },
        { name: "快进次数", type: "line", data: kjNumberData },
        { name: "快退次数", type: "line", data: ktNumberData },
      ];
    },

    // 初始化或更新图表
    getChart() {
      this.processData();
      if (!this.option.xAxis.data.length) {
        // 没有数据时不绘制图表
        return;
      }
      const myChart = echarts.init(this.$refs.myChart);
      myChart.setOption(this.option);
      // 确保图表在窗口大小变化时自适应
      window.addEventListener("resize", myChart.resize);
      // 清理前一个图表实例的resize事件
      this.$once("hook:beforeDestroy", () => {
        window.removeEventListener("resize", myChart.resize);
        myChart.dispose();
      });
    },

    // 选择视频，显示详情弹窗并更新数据
    selectVideo(item) {
      console.log("查看视频详情：", item);
      this.fetchVideoDetail(item.id);
      this.show = true; // 显示弹窗
    },

    // 获取视频列表数据
    async fetchVideoList() {
      try {
        const res = await listAll();
        console.log("hi")
        console.log(res)
        this.videoList = res.data;
      } catch (error) {
        console.error("获取视频列表失败:", error);
        this.$message.error("获取视频列表失败");
      }
    },

    // 获取所有视频的观看数据（用于统计图）
    async fetchVideoDetailView() {
      try {
        const data = {
          videoId: null, // null 表示获取所有视频的数据
        };
        const res = await videoNumber(data);
        this.videoDetailList = res.data;
        console.log("hii")
        console.log(res)
      } catch (error) {
        console.error("获取视频观看数据失败:", error);
        this.$message.error("获取视频观看数据失败");
      }
    },

    // 获取选定视频的观看详情
    async fetchVideoDetail(videoId) {
      try {
        const data = {
          videoId: videoId,
        };
        const res = await videoNumber(data);
        this.videoDetailList = res.data;
      } catch (error) {
        console.error("获取视频详情失败:", error);
        this.$message.error("获取视频详情失败");
      }
    },

    // 计算视频质量
    calculateVideoQuality() {
      if (!this.videoDetailList.length) {
        this.videoQuality = 0;
        return;
      }

      // 计算播放完成率
      const totalPlay = this.videoDetailList.reduce((sum, item) => sum + item.playNumber, 0);
      const totalEnded = this.videoDetailList.reduce((sum, item) => sum + item.endedNumber, 0);
      const completionRate = totalPlay ? totalEnded / totalPlay : 0;

      // 计算平均播放时长占比
      const totalCurrTime = this.videoDetailList.reduce((sum, item) => sum + item.currTime, 0);
      const avgDurationRatio = this.maxDuration ? totalCurrTime / (this.maxDuration * this.videoDetailList.length) : 0;

      // 快进和快退次数的总和
      const totalKj = this.videoDetailList.reduce((sum, item) => sum + item.kjNumber, 0);
      const totalKt = this.videoDetailList.reduce((sum, item) => sum + item.ktNumber, 0);

      // 定义权重（可以调整）
      const w1 = 0.4; // 播放完成率的权重
      const w2 = 0.4; // 平均播放时长的权重
      const w3 = -0.01; // 快进次数的权重（负面影响）
      const w4 = -0.01; // 快退次数的权重（负面影响）

      // 计算视频质量得分
      this.videoQuality = (
        w1 * completionRate +
        w2 * avgDurationRatio +
        w3 * (totalKj / totalPlay) +
        w4 * (totalKt / totalPlay)
      ).toFixed(2);
    },
  },
};
</script>

<style lang="scss" scoped>
.list-view {
  padding: 20px;
}

.video-quality-summary {
  margin-top: 20px;
  font-size: 16px;
}

.video-quality {
  margin-top: 20px;
  font-size: 16px;
}
</style>
