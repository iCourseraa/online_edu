<template>
  <el-card class="current-courses-card">
    <div v-if="currentCourses.length === 0" class="empty-message">暂无在学课程</div>
    <div v-else>
      <el-row :gutter="20">
        <el-col :span="8" v-for="(course, index) in currentCourses" :key="index">
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
  name: 'CurrentCourses',
  data() {
    return {
      currentCourses: [
        { title: 'Vue.js 实战', image: 'https://example.com/vue2.jpg' },
        { title: 'React 开发技巧', image: 'https://example.com/react.jpg' }
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
