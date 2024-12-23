<template>
  <div class="vismooc-container">
    <!-- 列表视图 -->
    <div class="list-view">
      <h3>视频列表</h3>
      <el-table :data="videoList" border style="width: 100%">
        <el-table-column prop="title" label="视频标题" />
        <el-table-column prop="length" label="视频时长" />
        <el-table-column prop="views" label="观看次数" />
        <el-table-column label="操作">
          <template #default="scope">
            <el-button size="small" @click="selectVideo(scope.row.id)">
              查看详情
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <!-- 内容视图 -->
    <div class="content-view">
      <h3>内容分析视图</h3>
      <div>
        <ve-bar :data="eventData" :settings="barSettings" style="height: 300px;" />
      </div>
      <div>
        <ve-line :data="seekData" :settings="lineSettings" style="height: 300px;" />
      </div>
    </div>

    <!-- 仪表板视图 -->
    <div class="dashboard-view">
      <h3>仪表板视图</h3>
      <el-row>
        <el-col :span="12">
          <el-card>
            <h4>地理分布</h4>
            <ve-pie :data="geoData" :settings="pieSettings" style="height: 300px;" />
          </el-card>
        </el-col>
        <el-col :span="12">
          <el-card>
            <h4>时间动态</h4>
            <div id="calendar-chart" style="height: 300px;"></div>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import VeBar from "v-charts/lib/bar.common";
import VeLine from "v-charts/lib/line.common";
import VePie from "v-charts/lib/pie.common";
import echarts from "echarts";

export default {
  name: "Clickstream",
  components: { VeBar, VeLine, VePie },
  data() {
    return {
      videoList: [
        { id: 1, title: "视频1", length: "10:00", views: 1000 },
        { id: 2, title: "视频2", length: "15:30", views: 1500 },
      ],
      selectedVideoId: null,
      eventData: {
        columns: ["时间", "播放", "暂停", "查找"],
        rows: [
          { 时间: "0:00", 播放: 100, 暂停: 50, 查找: 20 },
          { 时间: "0:30", 播放: 120, 暂停: 30, 查找: 15 },
        ],
      },
      barSettings: {
        stack: { 播放: "事件", 暂停: "事件", 查找: "事件" },
      },
      seekData: {
        columns: ["起点", "终点", "频次"],
        rows: [
          { 起点: "0:00", 终点: "0:30", 频次: 50 },
          { 起点: "0:30", 终点: "1:00", 频次: 30 },
        ],
      },
      lineSettings: {
        area: true,
      },
      geoData: {
        columns: ["国家", "观看次数"],
        rows: [
          { 国家: "美国", 观看次数: 500 },
          { 国家: "中国", 观看次数: 400 },
        ],
      },
      pieSettings: {
        roseType: "radius",
      },
      calendarData: [
        ["2024-12-01", 10],
        ["2024-12-02", 120],
        ["2024-12-03", 30],
      ],
    };
  },
  mounted() {
    this.initCalendar();
  },
  methods: {
    selectVideo(videoId) {
      this.selectedVideoId = videoId;
      console.log("选择的视频ID:", videoId);
      // TODO: 根据选中视频加载对应点击流数据
    },
    initCalendar() {
      const calendarChart = echarts.init(document.getElementById("calendar-chart"));
      const option = {
        tooltip: {
          trigger: "item",
        },
        visualMap: {
          min: 0,
          max: 150,
          calculable: true,
          orient: "horizontal",
          left: "center",
          bottom: "15%",
        },
        calendar: {
          range: "2024-12",
        },
        series: [
          {
            type: "heatmap",
            coordinateSystem: "calendar",
            data: this.calendarData,
          },
        ],
      };
      calendarChart.setOption(option);
    },
  },
};
</script>

<style scoped>
.vismooc-container {
  display: flex;
  flex-direction: column;
  gap: 20px;
}
.list-view,
.content-view,
.dashboard-view {
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 4px;
  background: #fff;
}
</style>
