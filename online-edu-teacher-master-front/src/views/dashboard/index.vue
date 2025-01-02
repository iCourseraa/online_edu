<template>
  <div class="app-container">
    <div style="margin-bottom: 24px">
      <span>{{ getTimeState }}！</span>
      <span>尊敬的讲师<strong>「{{ user.name || '' }}」</strong>！</span>
      <span>欢迎您使用在线教育平台！</span>
    </div>
    <el-row :gutter="20">
      <el-col :xs="12" :sm="6">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span class="cardName">已上架课程数量</span>
          </div>
          <div class="cardContent clearfix">
            <el-tooltip effect="dark" placement="top">
              <div slot="content">已上架的课程总数量为：{{ stat.courseCount }}</div>
              <span>{{ stat.courseCount }}</span>
            </el-tooltip>
          </div>
        </el-card>
      </el-col>
      <el-col :xs="12" :sm="6">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span class="cardName">课程视频数量</span>
          </div>
          <div class="cardContent clearfix">
            <el-tooltip effect="dark" placement="top">
              <div slot="content">已上架课程视频总数量为：{{ stat.videoCount }}</div>
              <span>{{ stat.videoCount }}</span>
            </el-tooltip>
          </div>
        </el-card>
      </el-col>
      <el-col :xs="12" :sm="6">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span class="cardName">评论数量</span>
          </div>
          <div class="cardContent clearfix">
            <el-tooltip effect="dark" placement="top">
              <div slot="content">评论总数量为：{{ stat.commentCount }}</div>
              <span>{{ stat.commentCount }}</span>
            </el-tooltip>
            <el-tooltip effect="dark" placement="top">
              <div slot="content">{{ stat.auditingCommentCount }} 条评论待审核</div>
              <span style="float: right;color: #21BA45">{{ stat.auditingCommentCount }}</span>
            </el-tooltip>
          </div>
        </el-card>
      </el-col>
      <el-col :xs="12" :sm="6">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span class="cardName">加入时间</span>
          </div>
          <div class="cardContent clearfix">
            <el-tooltip effect="dark" placement="top">
              <div slot="content">您于 {{ stat.joinDateTime }} 加入本平台</div>
              <span>{{ stat.joinDaysCount }} <span style="font-size: 15px">天</span></span>
            </el-tooltip>
          </div>
        </el-card>
      </el-col>
      <!-- 每日统计量 -->
      <el-col :span="24">
        <el-card class="box-card">
          <div slot="header" class="clearfix" style="height: 28px;line-height: 28px">
            <span class="cardName">每日数据统计</span>
            <div class="cardName" style="float: right">
              统计日期区间：
              <el-date-picker
                v-model="beginToEnd"
                style="width: 260px"
                size="mini"
                type="daterange"
                :picker-options="pickerOptions"
                :clearable="false"
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                format="yyyy-MM-dd"
                value-format="yyyy-MM-dd HH:mm:ss"
              />
            </div>
          </div>
          <v-stat-daily :start-end="beginToEnd" />
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>

import { mapGetters } from 'vuex'
// import { getDaily } from '@/api/stat'
import { getNotReadMessageCount } from '@/api/message'
import { getStat } from '@/api/stat'
import { parseTime } from '@/utils'

export default {
  name: 'Dashboard',
  components: {
    'v-stat-daily': () => import('@/views/dashboard/daily')
  },
  data() {
    return {
      stat: {
        auditingCommentCount: 0,
        commentCount: 0,
        courseCount: 0,
        joinDateTime: '',
        joinDaysCount: 0,
        videoCount: 0
      },

      pickerOptions: {
        shortcuts: [{
          text: '最近半个月',
          onClick(picker) {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 14)
            picker.$emit('pick', [start, end])
          }
        }, {
          text: '最近一个月',
          onClick(picker) {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30)
            picker.$emit('pick', [start, end])
          }
        }, {
          text: '最近两个月',
          onClick(picker) {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 60)
            picker.$emit('pick', [start, end])
          }
        }, {
          text: '最近三个月',
          onClick(picker) {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90)
            picker.$emit('pick', [start, end])
          }
        }]
      },
      // [begin, end]
      beginToEnd: [
        parseTime(new Date(new Date(new Date().toLocaleDateString()).getTime()).getTime() - 30 * 24 * 60 * 60 * 1000, ''),
        parseTime(new Date(new Date(new Date().toLocaleDateString()).getTime()).getTime(), '')
      ]
    }
  },
  computed: {
    ...mapGetters([
      'user',
      'messageCount'
    ]),
    getTimeState() {
      const hour = new Date().getHours()
      let text = ''
      if (hour < 6) {
        text = '凌晨好'
      } else if (hour < 9) {
        text = '早上好'
      } else if (hour < 12) {
        text = '上午好'
      } else if (hour < 14) {
        text = '中午好'
      } else if (hour < 17) {
        text = '下午好'
      } else if (hour < 19) {
        text = '傍晚好'
      } else if (hour < 22) {
        text = '晚上好'
      } else {
        text = '夜里好'
      }
      return text
    },
    defaultBeginToEnd() {
      const now = new Date(new Date(new Date().toLocaleDateString()).getTime()).getTime()
      const start = parseTime(now - 30 * 24 * 60 * 60 * 1000, '')
      const end = parseTime(now, '')
      return [start, end]
    }
  },
  created() {
    this.getMessageCount()
    this.getStat()
  },
  methods: {
    // 获取消息数量
    getMessageCount() {
      getNotReadMessageCount().then(resp => {
        this.$store.dispatch('user/setMessageCount', resp.data)
      })
    },
    getStat() {
      getStat().then(resp => {
        this.stat = resp.data
      })
    }
  }
}
</script>

<style lang="scss">
.el-col {
  margin-bottom: 20px;

  &:last-child {
    margin-bottom: 0;
  }
}

.el-card {
  &__header, &__body {
    padding: 12px 16px;
    line-height: 16px;
  }
}
</style>

<style lang="scss" scoped>
.box-card {
  .cardName {
    color: #97a8be;
  }

  .cardLink {
    float: right;
    padding: 0;
  }

  .cardContent {
    font-size: 26px;
    padding: 12px 0;
  }
}
</style>
