<template>
  <div class="avatar-box">
    <div>
      <el-tooltip style="display: inline-block" effect="dark" placement="right">
        <div slot="content">点击修改头像<br>图片大小不超过1MB</div>
        <el-upload
          action=""
          :show-file-list="false"
          accept=".png, .jpg, .jpeg"
          :http-request="updateAvatar"
        >
          <el-avatar :size="100" :src="user.avatar" fit="cover" class="avatar">
            <img alt="" :src="user.avatar" />
          </el-avatar>
        </el-upload>
      </el-tooltip>
    </div>
    <p class="nickname">{{ user.nickname || '用户昵称' }}</p>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { updateAvatar } from '@/api/user'
import { jsonObj2FormData } from '@/utils'

export default {
  name: 'LInfo',
  computed: {
    ...mapGetters(['user'])
  },
  methods: {
    updateAvatar(data) {
      const file = data.file
      updateAvatar(jsonObj2FormData({ file: file })).then(resp => {
        if (resp.status === 200) {
          this.$message.success(resp.message)
          const user = this.user
          user.avatar = resp.data
          this.$store.dispatch('user/setInfo', user)
        }
      })
    }
  }
}
</script>

<style scoped lang="scss">
.avatar-box {
  text-align: center;
  .avatar {
    cursor: pointer;
  }
  .nickname {
    font-size: 18px;
    margin: 7px 0;
  }
}
</style>
