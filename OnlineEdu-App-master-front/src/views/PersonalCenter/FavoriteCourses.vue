<template>
  <el-card class="favorite-card">
    <div v-if="favoriteCourses.length === 0" class="empty-message">暂无收藏课程</div>
    <div v-else>
      <el-row :gutter="20">
        <el-col :span="8" v-for="(course, index) in favoriteCourses" :key="index">
          <el-card class="course-item">
            <img :src="course.image" alt="" class="course-image"/>
            <div class="course-title">{{ course.title }}</div>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </el-card>
</template>

<script>
export default {
  name: 'FavoriteCourses',
  data() {
    return {
      favoriteCourses: [
        { title: 'Vue.js 入门', image: 'https://example.com/vue.jpg' },
        { title: 'JavaScript 高级编程', image: 'https://example.com/js.jpg' }
      ]
    }
  }
}
</script>

<style scoped lang="scss">
.course-item {
  .course-image {
    width: 100%;
    height: auto;
  }
  .course-title {
    font-size: 16px;
    margin-top: 10px;
  }
}
</style>
