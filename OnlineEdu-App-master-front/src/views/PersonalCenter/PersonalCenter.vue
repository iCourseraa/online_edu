<template>
  <el-row type="flex" justify="center">
    <el-col :span="20" style="margin-top: 30px">
      <el-row :gutter="24">
        <!-- 用户信息部分 -->
        <el-col :span="8">
          <el-card class="info-card">
            <div slot="header">用户信息</div>
            <v-l-info />
          </el-card>
        </el-col>

        <!-- 我的课程部分 -->
        <el-col :span="16">
          <el-card class="course-card">
            <div slot="header">我的课程</div>
            <el-tabs type="border-card">
              <!-- 课程提醒 -->
              <el-tab-pane>
                <span slot="label"><i class="tabs-icon el-icon-bell" />课程提醒</span>
                <v-course-reminder />
              </el-tab-pane>

              <!-- 收藏课程 -->
              <el-tab-pane>
                <span slot="label"><i class="tabs-icon el-icon-star" />收藏课程</span>
                <v-favorite-courses />
              </el-tab-pane>

              <!-- 最近在学 -->
              <el-tab-pane>
                <span slot="label"><i class="tabs-icon el-icon-film" />最近在学</span>
                <v-current-courses />
              </el-tab-pane>
            </el-tabs>
          </el-card>
        </el-col>
      </el-row>
    </el-col>
  </el-row>
</template>

<script>
export default {
  name: 'Profile',
  components: {
    'v-l-info': () => import('@/views/profile/l-info'),
    'v-course-reminder': () => import('@/views/PersonalCenter/CourseReminder'),
    'v-favorite-courses': () => import('@/views/PersonalCenter/FavoriteCourses'),
    'v-current-courses': () => import('@/views/PersonalCenter/CurrentCourses')
  },
  data() {
    return {};
  },
  methods: {}
}
</script>

<style lang="scss">
.el-card {
  &__header, &__body {
    padding: 12px;
  }
}

.course-card {
  .el-card__body {
    padding: 0;
  }

  .el-tabs {
    border-top: none;
    border-left: none;
    border-right: none;
  }

  .tabs-icon {
    margin-right: 5px;
  }
  .el-card__header {
    font-size: 18px;
    font-weight: bold;
  }
  .el-tabs {
    font-size: 20px; /* 增大选项卡字体 */
  }

  .el-tab-pane {
    padding: 22px; /* 增加内边距，给每个选项卡内容更多空间 */
  }

  width: 100%;
}

</style>
