<template>
  <el-card class="current-courses-card">
    <div v-if="favoriteCourses.length === 0" class="empty-message">暂无在学课程</div>
    <div v-else>
      <el-row :gutter="20" class="course-list">
        <el-col :span="6" v-for="(course, index) in paddedFavoriteCourses" :key="index">
          <router-link v-if="!isVirtualCourse(course)" :to="{ path: '/course/detail/' + course.id }"
            class="course-item-link">
            <div class="course-item">
              <img :src="course.image" alt="Course Image" class="course-image" />
              <div class="course-title">{{ course.title }}</div>
            </div>
          </router-link>
        </el-col>
      </el-row>
    </div>
  </el-card>
</template>

<script>
import { relList } from '@/api/content';

export default {
  name: 'FavoriteCourses',
  data() {
    return {
      favoriteCourses: [],
      paddedFavoriteCourses: []
    };
  },
  methods: {
    async queryList() {
      try {
        const id = localStorage.getItem("token").substr(0, 4);
        const resp = await relList(id);
        console.log(resp)
        this.favoriteCourses = resp.data.filter(item => item.isFavorite).map(item => ({
          id: item.courseId,
          title: item.className,
          image: item.cover,
          isFavorite: item.isFavorite // 确保包含 isFavorite 属性
        }));
        this.padCourses();
      } catch (error) {
        console.error('Error fetching course list:', error);
      }
    },
    padCourses() {
      // Calculate the number of rows needed
      const rowCount = Math.ceil(this.favoriteCourses.length / 4);
      const totalCourses = rowCount * 4;

      // Create a new array with padded courses
      this.paddedFavoriteCourses = this.favoriteCourses.concat(
        Array(totalCourses - this.favoriteCourses.length).fill({
          id: null,
          title: '虚拟课程',
          image: 'path/to/default/image.jpg' // Replace with your default image path
        })
      );
    },
    isVirtualCourse(course) {
      return course.id === null;
    }
  },
  mounted() {
    this.queryList();
  }
};
</script>

<style scoped lang="scss">
.empty-message {
  text-align: center;
  font-size: 16px;
  color: #999;
}

/* 课程列表样式 */
.course-list {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  margin: 20px 0;
}

/* 单个课程样式 */
.course-item {
  text-align: center;
  padding: 10px 0;
  background-color: transparent;
  /* 透明背景 */
  transition: transform 0.3s ease;
  margin-bottom: 50px;
  /* 增加行间距 */
}

.course-item:hover {
  transform: scale(1.05);
  /* 悬停放大效果 */
}

/* 图片样式 */
.course-image {
  width: 100%;
  height: 150px;
  /* 固定高度 */
  object-fit: contain;
  /* 等比例缩放并保持原始宽高比 */
  border: none;
  /* 移除边框 */
  border-radius: 8px;
  /* 圆角边框 */
  box-shadow: none;
  /* 去掉阴影 */
}

/* 课程标题样式 */
.course-title {
  margin-top: 20px;
  /* 增加与图片的距离 */
  font-size: 18px;
  /* 增大字体大小 */
  color: #333;
  font-weight: bold;
  /* 加粗字体 */
  word-break: break-word;
  /* 防止溢出 */
}
</style>
