<template>
  <el-card class="reminder-card">
    <div v-if="reminders.length === 0" class="empty-message">暂无课程提醒</div>
    <div v-else>
      <el-timeline>
        <el-timeline-item
          v-for="(reminder, index) in reminders"
          :key="index"
          :timestamp="reminder.time"
          :icon="reminder.icon"
        >
          <div>{{ reminder.message }}</div>
        </el-timeline-item>
      </el-timeline>
    </div>
  </el-card>
</template>

<script>
export default {
  name: 'CourseReminder',
  data() {
    return {
      reminders: [
        { time: '2024-12-16 10:00', icon: 'el-icon-time', message: '新的课程发布，请按时查看。' },
        { time: '2024-12-17 14:00', icon: 'el-icon-info', message: '有课程即将过期，请尽快完成。' }
      ]
    }
  }
}
</script>

<style scoped lang="scss">
.empty-message {
  text-align: center;
  font-size: 16px;
  color: #999;
}
.el-timeline-item .el-icon {
  font-size: 30px; /* 增大图标大小 */
}
</style>
