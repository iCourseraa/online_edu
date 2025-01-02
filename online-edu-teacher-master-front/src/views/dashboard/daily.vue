<template>
  <div>
    <!-- 切换按钮 -->
    <div>
      <button :class="{ 'active': chartType === 'view' }" @click="changeChartType('view')">课程观看量</button>
      <button :class="{ 'active': chartType === 'buy' }" @click="changeChartType('buy')">课程收藏量</button>
    </div>
    <!-- 图表组件 -->
    <ve-line v-loading="loading" :data="chartData" :settings="chartSettings" :extend="extend" />
  </div>
</template>

<script>
// import { getCommon } from '@/api/stat'  // 假设这是你用来获取数据的 API 服务
import { getDaily } from '@/api/stat'
import { jsonObj2FormData } from '@/utils'

export default {
  name: 'StatDaily',
  props: {
    startEnd: {
      type: Array,
      default: function () {
        const now = new Date(new Date(new Date().toLocaleDateString()).getTime()).getTime()
        const start = this.parseTime(now - 30 * 24 * 60 * 60 * 1000) // 默认过去30天
        const end = this.parseTime(now)
        return [start, end]
      }
    }
  },
  data() {
    return {
      loading: false,
      chartType: 'view',
      chartData: {
        columns: ['date'], // 先只设置日期列
        rows: [],
        series: [] // 新增系列数据
      },
      chartSettings: {
        labelMap: {
          courseBuyCount: '课程购买数量',
          videoViewCount: '视频观看数量'
        },
        area: true // 设为true可以使图表为区域图（如果需要）
      },
      extend: {
        legend: {
          left: '12'
        },
        grid: {
          bottom: '10'
        },
        xAxis: {
          axisLabel: {
            formatter: function (value) {
            // 只保留日期部分
              return value.split(' ')[0]
            }
          }
        }
      },
      colorPalette: ['#42b983', '#ff6a00'] // 定义颜色调色板
      // colorPalette: ['#42b983', '#ff6a00', '#409eff', '#e03c31', '#9c27b0', '#2196f3'] // 定义颜色调色板
    }
  },
  watch: {
    startEnd(val) {
      this.getDailyStat()
    }
  },
  created() {
    this.getDailyStat()
  },
  methods: {
    // 获取课程统计数据的 API 请求
    getDailyStat() {
      this.loading = true
      const params = jsonObj2FormData({
        start: this.startEnd[0],
        end: this.startEnd[1]
      })

      getDaily(params).then(response => {
        console.log('API 返回的数据:', response.data)
        const courses = response.data

        // 根据图表类型设置数据
        this.chartData.columns = ['date'] // 初始化日期列
        this.chartData.rows = [] // 清空之前的数据
        this.chartData.series = [] // 清空之前的系列数据

        // 处理数据，生成观看量和订阅量数据
        const { viewData, buyData } = this.processCourseData(courses)

        // 设置最终的图表数据
        if (this.chartType === 'view') {
          this.chartData.columns = ['date', ...viewData.columns]
          this.chartData.rows = viewData.rows
          this.chartData.series = viewData.series
        } else if (this.chartType === 'buy') {
          this.chartData.columns = ['date', ...buyData.columns]
          this.chartData.rows = buyData.rows
          this.chartData.series = buyData.series
        }

        console.log('最终的 chartData:', this.chartData)
        this.loading = false
      }).catch(() => {
        this.loading = false
      })
    },

    // 处理课程数据，分别生成观看量和订阅量数据
    processCourseData(courses) {
      const viewData = {
        columns: [],
        rows: [],
        series: []
      }
      const buyData = {
        columns: [],
        rows: [],
        series: []
      }

      const allDates = this.getAllDates(courses)

      // 遍历所有课程
      courses.forEach(course => {
        const viewCourseData = []
        const buyCourseData = []

        // 遍历该课程的每日数据
        allDates.forEach(date => {
          const dailyStat = course.dailyStats.find(stat => stat.date === date) || { videoViewCount: 0, courseBuyCount: 0 }

          // 将每天的数据填充到观看量和订阅量表格
          viewCourseData.push(dailyStat.videoViewCount)
          buyCourseData.push(dailyStat.courseBuyCount)
        })

        // 为观看量表添加课程数据
        viewData.columns.push(course.courseTitle)
        viewData.rows = this.mergeRows(viewData.rows, allDates, viewCourseData, course.courseTitle)
        viewData.series.push({
          name: course.courseTitle,
          data: viewCourseData,
          color: this.colorPalette[viewData.series.length % this.colorPalette.length]
        })

        // 为订阅量表添加课程数据
        buyData.columns.push(course.courseTitle)
        buyData.rows = this.mergeRows(buyData.rows, allDates, buyCourseData, course.courseTitle)
        buyData.series.push({
          name: course.courseTitle,
          data: buyCourseData,
          color: this.colorPalette[buyData.series.length % this.colorPalette.length]
        })
      })

      return { viewData, buyData }
    },

    // 获取所有日期（从所有课程的 dailyStats 中获取）
    getAllDates(courses) {
      const dates = new Set()
      courses.forEach(course => {
        course.dailyStats.forEach(stat => {
          dates.add(stat.date)
        })
      })
      return [...dates].sort() // 按日期排序
    },

    // 合并 rows 数据
    mergeRows(rows, dates, courseData, courseTitle) {
      dates.forEach((date, index) => {
        if (rows[index] === undefined) {
          rows[index] = { date }
        }
        rows[index][courseTitle] = courseData[index]
      })
      return rows
    },

    // 时间格式化方法
    parseTime(time) {
      const date = new Date(time)
      return `${date.getFullYear()}-${(date.getMonth() + 1).toString().padStart(2, '0')}-${date.getDate().toString().padStart(2, '0')}`
    },

    // 切换图表类型
    changeChartType(type) {
      this.chartType = type
      this.getDailyStat() // 切换图表类型后重新获取数据
    }
  }
}
</script>

<style scoped>
/* 按钮容器 */
button {
  margin-right: 10px;
  padding: 8px 16px;
  /* 增大按钮的大小，方便点击 */
  cursor: pointer;
  border: none;
  /* 去掉默认的边框 */
  border-radius: 4px;
  /* 圆角 */
  background-color: #f1f1f1;
  /* 默认背景颜色 */
  color: #97a8be;
  /* 默认字体颜色 */
  font-size: 14px;
  /* 设置字体大小 */
  font-weight: 500;
  /* 设置字体加粗 */
  transition: all 0.3s ease;
  /* 平滑的过渡效果 */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  /* 增加阴影效果，提升层次感 */
}

/* 活动按钮样式 */
button.active {
  background-color: #f8f7f7;
  /* 激活时的背景颜色，淡蓝色 */
  color: #4b9af0;
  /* 激活时的字体颜色，深蓝色 */
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  /* 激活时按钮的阴影效果，蓝色阴影 */
}

/* 鼠标悬停时的效果 */
button:hover {
  background-color: #e0e0e0;
  /* 鼠标悬停时的背景色 */
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  /* 悬停时的阴影效果 */
}

/* 按钮被禁用时的样式 */
button:disabled {
  background-color: #dcdcdc;
  /* 禁用时的背景色 */
  color: #b0b0b0;
  /* 禁用时的字体颜色 */
  cursor: not-allowed;
  /* 禁用时的鼠标样式 */
  box-shadow: none;
  /* 禁用时去掉阴影 */
}
</style>
