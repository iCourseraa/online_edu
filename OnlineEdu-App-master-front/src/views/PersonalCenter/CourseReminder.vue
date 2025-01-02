<template>
  <el-card class="reminder-card">
    <div v-if="favoriteCourses.length === 0" class="empty-message">暂无课程提醒</div>
    <div v-else>
      <el-row :gutter="20" class="course-list">
        <el-col :span="24" v-for="(course, index) in favoriteCourses" :key="index">
          <el-card class="course-item">
            <!-- 使用 router-link 包裹课程标题 -->
            <router-link :to="{ path: '/course/detail/' + course.id }" class="course-title-link">
              <div class="course-title">{{ course.title }}</div>
            </router-link>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </el-card>
</template>

<script>
import { listWarning } from '@/api/content'

export default {
  name: 'CourseReminder',
  data() {
    return {
      favoriteCourses: [
        {
          id: '', // 确保每个课程都有一个id字段
          title: ''
        }
      ]
    }
  },
  methods: {
    async queryList() {
      var id = localStorage.getItem("token").substr(0, 4);
      try {
        const resp = await listWarning(id);
        console.log('Response:', resp);
        this.favoriteCourses = resp.data.map(item => ({
          id: item.courseId, // 确保这里使用了正确的字段名
          title: item.className
        }));
      } catch (error) {
        console.error('Error fetching course list:', error);
      }
    }
  },
  mounted() {
    this.queryList();
  }
}
</script>

<style scoped lang="scss">
.empty-message {
  text-align: center;
  font-size: 16px;
  color: #999;
}

.course-title-link {
  text-decoration: none; /* 移除默认链接样式 */
  color: inherit; /* 继承父元素的颜色 */
  display: block; /* 确保链接占据整个卡片区域 */
}

.course-title-link:hover {
  cursor: pointer; /* 当鼠标悬停在链接上时改变光标形状 */
  color: #409EFF; /* 添加悬停颜色 */
}

/* 美化课程项 */
.course-item {
  border: 1px solid #ddd;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.15); /* 修改阴影效果 */
  text-align: center;
  padding: 20px; /* 增加内边距 */
  background-color: #f9f9f9; /* 更改背景颜色 */
  transition: transform 0.3s ease, box-shadow 0.3s ease; /* 添加阴影效果的过渡 */
  border-radius: 10px; /* 添加圆角 */
}

.course-item:hover {
  transform: translateY(-5px); /* 添加悬停提升效果 */
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2); /* 悬停时增加阴影 */
}

/* 课程标题样式 */
.course-title {
  font-size: 18px; /* 增大字体大小 */
  color: #333;
  font-weight: bold; /* 加粗字体 */
  margin: 0; /* 移除默认外边距 */
  padding: 10px 0; /* 增加内边距 */
  border-bottom: 1px solid #eee; /* 添加底部边框 */
}
</style>
