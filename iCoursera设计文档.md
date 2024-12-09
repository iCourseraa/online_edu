# iCoursera产品设计文档

> 第三组
>
> 项目成员： 李安鑫 卞卡 干凯洁 崔彦 吴霁航 李雅琪 李思琪 朱俊哲（排名不分先后）
>
> 产品名：iCoursera在线课堂平台

## 一、项目介绍

**iCoursera** 是一个面向教育领域的在线学习平台，旨在通过整合丰富的课程资源和高效的管理系统，为学生、讲师和管理员提供便捷且全面的学习与教学体验。平台设计注重用户需求，分为学生端、讲师端和管理员端三大模块，各自具备针对性功能，确保不同角色的需求得到满足，实现教育资源的高效流通与管理。

**iCoursera** 基于**SpringBoot+Vue**前后端分离的在线教育平台项目，单体应用服务架构。系统共设计三种角色：管理员、讲师和学员，三个角色分别对应一个操作端。也就是本系统1个后台项目，三个前端项目。管理员端没有引入角色和权限管理，只有一个角色。

已实现的功能列表展示：

**管理员端：**

- 数据统计
- 轮播图管理
- 课程管理
  - 课程列表
  - 课程审核
  - 分类管理
- 讲师管理
  - 讲师列表
  - 讲师审核
- 学员管理
- 用户管理
- 订单管理

**讲师端：**

- 发布课程
- 课程管理
- 评论管理
- 消息接收

**学员端（网站首页）：**

- 登录注册
- 分类与轮播图展示
- 课程列表展示
- 课程搜索（关键词、分类、讲师）
- 课程详情（播放器、课程介绍、评论、讲师简介、订阅）
- 订阅订单
- 讲师入驻

## 二、需求整理

### 二、用户需求分析

随着互联网技术的飞速发展，在线教育已成为现代教育的重要模式。生活节奏的加快使用户的时间日益碎片化，他们迫切需要一个能够随时随地学习的平台，以满足不同场景下的学习需求。同时，系统化的学习进度跟踪与提醒功能，能够帮助用户全面了解学习情况，从而提升效率并优化学习路径。

在数字化学习环境中，个性化体验已成为用户关注的重点。用户不仅希望能够收藏感兴趣的课程，方便回顾，还期待通过学习数据生成个性化报告，以掌握学习节奏、评估，成果，进而调整学习计划。与此同时，内容生产者（讲师）也渴望拥有便捷的平台，用于发布和管理课程，并通过数据分析优化教学内容。

在平台管理层面，确保课程内容质量与合规性、维护平台秩序、提升优质内容的曝光率，是在线教育平台持续发展的关键。通过优化平台运营与用户体验，构建一个内容丰富、管理规范的生态系统，将满足多样化的学习需求，推动在线教育迈向更高水平。

### 2.用户定位

1. 学生端用户：学习需求；个性化学习体验；学习进度提醒；互动与交流功能
2. 讲师端用户：课程创建与管理；教学效果反馈；互动与答疑功能；内容发布与审核
3. 管理员端用户：课程质量与审核；用户管理；平台管理；内容优化；数据分析与运营

## 三、技术选型

**开发环境**

- 工具：IntelliJ IDEA
- JDK 1.8
- 数据库：MySQL 8.0.15
- 项目构建：后端Maven、前端 webpack

**后端**

- Web框架：Spring Boot

- 字段校验：Spring Validation

- 持久层：[MyBatis-Plus](https://mybatis.plus/)

- 接口文档：Swagger2

- 缓存：Redis

- 工具：[Hutool](https://www.hutool.cn/)

- 资源存储：阿里云对象存储OSS

- 课程视频点播：阿里云视频点播VoD

- Lombok：请确保您的 IDE 安装了此插件 

**前端**

- Vue.js2 全家桶

- Element-UI

- [vue-admin-template](https://github.com/PanJiaChen/vue-admin-template) 后台模板

- axios

- 图表：v-charts（ECharts）

- 富文本编辑器：[wangEditor](https://github.com/wangeditor-team/wangEditor/)

## 四、产品整体流程和功能简介

### 1.产品核心流程图

![image-20241129123138966](https://gitee.com/tongxue-zhu/typora/raw/master/image-20241129123138966.png)

### 2.功能摘要

<table border="1">
  <thead>
    <tr>
      <th>模块</th>
      <th>功能模块</th>
      <th>主要功能点</th>
      <th>优先级</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td rowspan="15">学生端</td>
      <td rowspan="3">首页</td>
      <td>课程搜索</td>
      <td>高</td>
    </tr>
    <tr>
      <td>课程推荐</td>
      <td>高</td>
    </tr>
    <tr>
      <td>热门课程展示</td>
      <td>高</td>
    </tr>
    <tr>
      <td rowspan="2">学习中心</td>
      <td>学习课程列表</td>
      <td>高</td>
    </tr>
    <tr>
      <td>课程排序（按学习时间、加入时间）</td>
      <td>高</td>
    </tr>
    <tr>
      <td rowspan="2">收藏夹</td>
      <td>查看收藏列表</td>
      <td>中</td>
    </tr>
    <tr>
      <td>快速访问感兴趣课程</td>
      <td>中</td>
    </tr>
    <tr>
      <td rowspan="2">学习进度提醒</td>
      <td>进度提醒通知</td>
      <td>高</td>
    </tr>
    <tr>
      <td>个性化学习报告</td>
      <td>高</td>
    </tr>
    <tr>
      <td rowspan="6">我</td>
      <td>修改昵称</td>
      <td>中</td>
    </tr>
    <tr>
      <td>设置头像</td>
      <td>中</td>
    </tr>
    <tr>
      <td>修改性别</td>
      <td>低</td>
    </tr>
    <tr>
      <td>真实姓名管理</td>
      <td>高</td>
    </tr>
    <tr>
      <td>设置常用邮箱</td>
      <td>中</td>
    </tr>
    <tr>
      <td>绑定手机号</td>
      <td>高</td>
    </tr>
    <tr>
      <td rowspan="9">讲师端</td>
      <td rowspan="2">首页</td>
      <td>仪表盘展示</td>
      <td>高</td>
    </tr>
    <tr>
      <td>数据可视化分析</td>
      <td>高</td>
    </tr>
    <tr>
      <td rowspan="4">课程管理</td>
      <td>课程发布与编辑</td>
      <td>高</td>
    </tr>
    <tr>
      <td>课程修改与更新</td>
      <td>高</td>
    </tr>
    <tr>
      <td>课程列表查看与管理</td>
      <td>高</td>
    </tr>
    <tr>
      <td>评论审核与管理</td>
      <td>中</td>
    </tr>
    <tr>
      <td rowspan="3">查看</td>
      <td>浏览数据分析</td>
      <td>高</td>
    </tr>
        <tr>
      <td>全天浏览趋势分析</td>
      <td>高</td>
    </tr>
        <tr>
      <td>视频观看数据分析</td>
      <td>高</td>
    </tr>
    <tr>
      <td rowspan="8">管理端</td>
      <td rowspan="2">首页</td>
      <td>数据概况</td>
      <td>高</td>
    </tr>
    <tr>
      <td>数据统计分析</td>
      <td>高</td>
    </tr>
    <tr>
      <td>轮播管理</td>
      <td>轮播内容管理</td>
      <td>中</td>
    </tr>
    <tr>
      <td rowspan="3">课程管理</td>
      <td>课程列表查看与管理</td>
      <td>中</td>
    </tr>
    <tr>
      <td>课程审核与审批</td>
      <td>高</td>
    </tr>
    <tr>
      <td>课程分类管理</td>
      <td>高</td>
    </tr>
    <tr>
      <td rowspan="2">用户管理</td>
      <td>学员信息编辑</td>
      <td>高</td>
    </tr>
    <tr>
      <td>讲师信息编辑</td>
      <td>高</td>
    </tr>
  </tbody>
</table>


## 五、界面与交互

### 学生端

**1.登录与注册**	

- 点击进入iCoursea网站，会先跳出【登录】和【注册】页面。
![image-1](https://gitee.com/tongxue-zhu/typora/raw/master/image-1.png)
![image-2](https://gitee.com/tongxue-zhu/typora/raw/master/image-2.png)

**2.首页面**
- 用户登录后会进入【首页面】
![image-3](https://gitee.com/tongxue-zhu/typora/raw/master/image-3.png)
- 点击某一具体课程后：
![image-4](https://gitee.com/tongxue-zhu/typora/raw/master/image-4.png)
- 点击讨论区
![image-5](https://gitee.com/tongxue-zhu/typora/raw/master/image-5.png)
- 点击某一具体视频栏目 
![image-6](https://gitee.com/tongxue-zhu/typora/raw/master/image-6.png)
- 首页面最上面的是【搜索结果】
![image-7](https://gitee.com/tongxue-zhu/typora/raw/master/image-7.png)
![image-8](https://gitee.com/tongxue-zhu/typora/raw/master/image-8.png)

**3.学习中心**
- 点击学习中心
![image-9](https://gitee.com/tongxue-zhu/typora/raw/master/image-9.png)
- 收藏夹与此页面类似
![image-10](https://gitee.com/tongxue-zhu/typora/raw/master/image-10.png)

**4.信息编辑**
- 点击头像后  【我】页面
![image-11](https://gitee.com/tongxue-zhu/typora/raw/master/image-11.png)


### 讲师端
**1.仪表盘**
- 首页是一些仪表盘与数据可视化
![image-12](https://gitee.com/tongxue-zhu/typora/raw/master/image-12.png)

**2.消息界面**
- 消息界面：通知审核
![image-13](https://gitee.com/tongxue-zhu/typora/raw/master/image-13.png)

**3.课程管理**
- 点击课程发布按钮
![image-14](https://gitee.com/tongxue-zhu/typora/raw/master/image-14.png)
- 这是第一步，随后加入课程封面等内容，跳出提示
![image-15](https://gitee.com/tongxue-zhu/typora/raw/master/image-15.png)
- 第二步，新建章节
![image-16](https://gitee.com/tongxue-zhu/typora/raw/master/image-16.png)
![image-17](https://gitee.com/tongxue-zhu/typora/raw/master/image-17.png)
- 点击修改按钮
![image-18](https://gitee.com/tongxue-zhu/typora/raw/master/image-18.png)
- 点击确认，提交 到第三步骤
![image-19](https://gitee.com/tongxue-zhu/typora/raw/master/image-19.png)
- 课程列表区域
![image-20](https://gitee.com/tongxue-zhu/typora/raw/master/image-20.png)
- 点击修改
![image-21](https://gitee.com/tongxue-zhu/typora/raw/master/image-21.png)
- 点击修改章节按钮
![image-22](https://gitee.com/tongxue-zhu/typora/raw/master/image-22.png)
- 评论管理界面
![image-23](https://gitee.com/tongxue-zhu/typora/raw/master/image-23.png)
- 点击查看可以看到一些用户数据，观察数据
![image-24](https://gitee.com/tongxue-zhu/typora/raw/master/image-24.png)

### 管理员端
**1.首页样式**
![image-25](https://gitee.com/tongxue-zhu/typora/raw/master/image-25.png)

**2.轮播管理**
![image-26](https://gitee.com/tongxue-zhu/typora/raw/master/image-26.png)

**3.系统管理**

**(1).课程列表**
![image-27](https://gitee.com/tongxue-zhu/typora/raw/master/image-27.png)

- 点击编辑：
![image-28](https://gitee.com/tongxue-zhu/typora/raw/master/image-28.png)
**(2).课程管理**
![image-29](https://gitee.com/tongxue-zhu/typora/raw/master/image-29.png)
- 点击编辑：
![image-30](https://gitee.com/tongxue-zhu/typora/raw/master/image-30.png)
- 点击侧边栏的分类管理：
![image-31](https://gitee.com/tongxue-zhu/typora/raw/master/image-31.png)
- 点击添加一级分类：
![image-32](https://gitee.com/tongxue-zhu/typora/raw/master/image-32.png)
- 点击添加 可为一级分类添加子分类
![image-33](https://gitee.com/tongxue-zhu/typora/raw/master/image-33.png)
- 点击修改：
![image-34](https://gitee.com/tongxue-zhu/typora/raw/master/image-34.png)
**(3).人员管理**
- 点击学员：
![image-35](https://gitee.com/tongxue-zhu/typora/raw/master/image-35.png)
- 点击编辑：
![image-36](https://gitee.com/tongxue-zhu/typora/raw/master/image-36.png)
- 侧边栏点击讲师界面：
![image-37](https://gitee.com/tongxue-zhu/typora/raw/master/image-37.png)
- 点击添加讲师：
![image-38](https://gitee.com/tongxue-zhu/typora/raw/master/image-38.png)
- 点击编辑后：
![image-39](https://gitee.com/tongxue-zhu/typora/raw/master/image-39.png)

## 六、数据库设计

**数据库: online\_edu，统一编码：utf8mb4**

**1.表格: acl\_permission**

索引:

| 名称   | 类型 | 属性   | 备注 |
| ------ | ---- | ------ | ---- |
| 主索引 | id   | unique |      |

字段:

| 名称         | 类型                  | 空   | 默认值              | 属性 | 备注                                                         |
| ------------ | --------------------- | ---- | ------------------- | ---- | ------------------------------------------------------------ |
| id           | int\(11\) unsigned    | 否   | \<auto\_increment>  |      | 菜单权限表                                                   |
| pid          | int\(11\) unsigned    | 否   |                     |      | 父级id                                                       |
| type         | tinyint\(4\) unsigned | 否   | 1                   |      | 菜单类型，0顶部菜单、1聚合菜单、2页面菜单、3接口             |
| name         | varchar\(255\)        | 是   |                     |      | 路由名称或接口名称                                           |
| path         | varchar\(255\)        | 否   |                     |      | 菜单路径\(以http开头时，视为打开外部链接\) 或 请求接口的地址 |
| component    | varchar\(255\)        | 是   |                     |      | 菜单组件，只有叶子菜单才可配置                               |
| meta         | varchar\(1023\)       | 是   | \<空>               |      | 菜单的route.meta配置项，json格式                             |
| admin        | tinyint\(1\) unsigned | 否   | 0                   |      | 0默认，1只有超级管理员才能使用                               |
| enable       | tinyint\(1\) unsigned | 否   | 1                   |      | 是否启用，0否1是                                             |
| update\_time | datetime              | 是   | \<INSERT-TimeStamp> |      | 更新时间                                                     |
| create\_time | datetime              | 是   | \<INSERT-TimeStamp> |      | 创建时间                                                     |

**2.表格: acl\_role**

备注: 角色

索引:

| 名称   | 类型 | 属性   | 备注 |
| ------ | ---- | ------ | ---- |
| 主索引 | id   | unique |      |

字段:

| 名称           | 类型               | 空   | 默认值              | 属性 | 备注               |
| -------------- | ------------------ | ---- | ------------------- | ---- | ------------------ |
| id             | int\(11\) unsigned | 否   | \<auto\_increment>  |      | 角色id             |
| name           | varchar\(31\)      | 否   |                     |      | 角色名称           |
| permission\_id | varchar\(2048\)    | 是   |                     |      | 角色具有的权限ID串 |
| enable         | tinyint\(1\)       | 是   | 1                   |      | 是否启用，0否1是   |
| update\_time   | datetime           | 是   | \<INSERT-TimeStamp> |      | 更新时间           |
| create\_time   | datetime           | 是   | \<INSERT-TimeStamp> |      | 创建时间           |

**3.表格: acl\_user**

备注: 管理员用户表

索引:

| 名称         | 类型     | 属性   | 备注 |
| ------------ | -------- | ------ | ---- |
| 主索引       | id       | unique |      |
| uk\_username | username | unique |      |

字段:

| 名称         | 类型                  | 空   | 默认值              | 属性 | 备注             |
| ------------ | --------------------- | ---- | ------------------- | ---- | ---------------- |
| id           | int\(11\) unsigned    | 否   | \<auto\_increment>  |      | 用户id           |
| username     | varchar\(31\)         | 否   |                     |      | 用户名           |
| password     | varchar\(255\)        | 否   |                     |      | 密码             |
| nickname     | varchar\(31\)         | 是   |                     |      | 昵称             |
| avatar       | varchar\(1023\)       | 是   |                     |      | 用户头像         |
| mark         | varchar\(255\)        | 是   |                     |      | 备注             |
| sign         | varchar\(255\)        | 是   |                     |      | 用户签名         |
| roleId       | int\(11\) unsigned    | 否   |                     |      | 角色id           |
| enable       | tinyint\(1\) unsigned | 否   | 1                   |      | 是否启用，0否1是 |
| update\_time | datetime              | 是   | \<INSERT-TimeStamp> |      | 更新时间         |
| create\_time | datetime              | 是   | \<INSERT-TimeStamp> |      | 创建时间         |

**4.表格: edu\_chapter**

备注: 课程章节表

索引:

| 名称   | 类型 | 属性   | 备注 |
| ------ | ---- | ------ | ---- |
| 主索引 | id   | unique |      |

字段:

| 名称         | 类型               | 空   | 默认值              | 属性 | 备注     |
| ------------ | ------------------ | ---- | ------------------- | ---- | -------- |
| id           | int\(11\) unsigned | 否   | \<auto\_increment>  |      | 章节ID   |
| course\_id   | int\(11\) unsigned | 否   |                     |      | 课程ID   |
| title        | varchar\(63\)      | 否   |                     |      | 章节名称 |
| sort         | int\(11\)          | 是   | 0                   |      | 显示排序 |
| update\_time | datetime           | 是   | \<INSERT-TimeStamp> |      | 更新时间 |
| create\_time | datetime           | 是   | \<INSERT-TimeStamp> |      | 创建时间 |

**5.表格: edu\_chapter\_tmp**

备注: 课程章节临时表（用于存放二次修改的数据）

索引:

| 名称    | 类型 | 属性   | 备注 |
| ------- | ---- | ------ | ---- |
| 主索引  | id   | unique |      |
| idx\_id | oid  |        |      |

字段:

| 名称         | 类型                  | 空   | 默认值              | 属性 | 备注     |
| ------------ | --------------------- | ---- | ------------------- | ---- | -------- |
| id           | bigint\(20\) unsigned | 否   | 0                   |      | 主键     |
| oid          | int\(11\) unsigned    | 是   | 0                   |      | 原章节ID |
| course\_id   | int\(11\) unsigned    | 否   |                     |      | 课程ID   |
| title        | varchar\(63\)         | 否   |                     |      | 章节名称 |
| sort         | int\(11\)             | 是   | 0                   |      | 显示排序 |
| update\_time | datetime              | 是   | \<INSERT-TimeStamp> |      | 更新时间 |
| create\_time | datetime              | 是   | \<INSERT-TimeStamp> |      | 创建时间 |

**6.表格: edu\_comment**

备注: 课程评论表

索引:

| 名称                        | 类型                   | 属性   | 备注 |
| --------------------------- | ---------------------- | ------ | ---- |
| 主索引                      | id                     | unique |      |
| idx\_course\_id             | course\_id             |        |      |
| idx\_member\_id             | member\_id             |        |      |
| idx\_member\_id\_course\_id | member\_id, course\_id |        |      |
| idx\_teacher\_id            | teacher\_id            |        |      |

字段:

| 名称         | 类型                  | 空   | 默认值              | 属性 | 备注                   |
| ------------ | --------------------- | ---- | ------------------- | ---- | ---------------------- |
| id           | int\(11\) unsigned    | 否   | \<auto\_increment>  |      | 评论ID                 |
| course\_id   | int\(11\) unsigned    | 否   |                     |      | 课程id                 |
| teacher\_id  | int\(11\) unsigned    | 否   |                     |      | 讲师id                 |
| member\_id   | int\(11\) unsigned    | 否   |                     |      | 会员id                 |
| content      | varchar\(1023\)       | 是   |                     |      | 评论内容               |
| mark         | double unsigned       | 是   | 5                   |      | 评分（满分5.00）       |
| status       | tinyint\(1\) unsigned | 是   | \<空>               |      | 评论状态 0审核中 1通过 |
| update\_time | datetime              | 是   | \<INSERT-TimeStamp> |      | 更新时间               |
| create\_time | datetime              | 是   | \<INSERT-TimeStamp> |      | 创建时间               |

**7.表格: edu\_course**

备注: 课程表

索引:

| 名称   | 类型 | 属性   | 备注 |
| ------ | ---- | ------ | ---- |
| 主索引 | id   | unique |      |

字段:

| 名称         | 类型                    | 空   | 默认值              | 属性                    | 备注                              |
| ------------ | ----------------------- | ---- | ------------------- | ----------------------- | --------------------------------- |
| id           | int\(11\) unsigned      | 否   | \<auto\_increment>  |                         | 课程ID                            |
| teacher\_id  | int\(11\) unsigned      | 否   |                     |                         | 课程讲师ID                        |
| subject\_id  | int\(11\) unsigned      | 否   |                     |                         | 课程科目分类ID                    |
| title        | varchar\(63\)           | 否   |                     |                         | 课程标题                          |
| price        | double\(10,2\) unsigned | 是   | 0.00                |                         | 课程销售价格，设置为0则可免费观看 |
| lesson\_num  | int\(11\) unsigned      | 否   | 0                   |                         | 总课时                            |
| cover        | varchar\(1023\)         | 否   |                     | utf8, utf8\_general\_ci | 课程封面图片路径                  |
| description  | text                    | 是   |                     |                         | 课程描述                          |
| buy\_count   | int\(11\) unsigned      | 是   | 0                   |                         | 销售数量                          |
| view\_count  | int\(11\) unsigned      | 是   | 0                   |                         | 浏览数量                          |
| sort         | int\(11\)               | 是   | 0                   |                         | 显示排序                          |
| enable       | tinyint\(1\)            | 否   | 1                   |                         | 上架下架，0下架 1上架             |
| status       | tinyint\(4\)            | 是   | 0                   |                         | 课程状态，草稿 审核 发表          |
| remarks      | varchar\(511\)          | 是   | \<空>               |                         | 备注                              |
| update\_time | datetime                | 是   | \<INSERT-TimeStamp> |                         | 更新时间                          |
| create\_time | datetime                | 是   | \<INSERT-TimeStamp> |                         | 创建时间                          |

**8.表格: edu\_subject**

备注: 课程科目分类表

索引:

| 名称            | 类型       | 属性   | 备注 |
| --------------- | ---------- | ------ | ---- |
| 主索引          | id         | unique |      |
| idx\_parent\_id | parent\_id |        |      |

字段:

| 名称         | 类型                  | 空   | 默认值              | 属性 | 备注             |
| ------------ | --------------------- | ---- | ------------------- | ---- | ---------------- |
| id           | int\(11\) unsigned    | 否   | \<auto\_increment>  |      | 课程类别ID       |
| title        | varchar\(15\)         | 否   |                     |      | 类别名称         |
| parent\_id   | int\(11\) unsigned    | 是   | 0                   |      | 父ID             |
| sort         | int\(11\) unsigned    | 是   | 0                   |      | 排序字段         |
| enable       | tinyint\(1\) unsigned | 否   | 1                   |      | 是否启用，0否1是 |
| update\_time | datetime              | 是   | \<INSERT-TimeStamp> |      | 更新时间         |
| create\_time | datetime              | 是   | \<INSERT-TimeStamp> |      | 创建时间         |

**9.表格: edu\_teacher**

备注: 讲师表

索引:

| 名称       | 类型   | 属性   | 备注 |
| ---------- | ------ | ------ | ---- |
| 主索引     | id     | unique |      |
| uk\_mobile | mobile | unique |      |
| uk\_name   | name   | unique |      |

字段:

| 名称         | 类型                  | 空   | 默认值              | 属性 | 备注                                   |
| ------------ | --------------------- | ---- | ------------------- | ---- | -------------------------------------- |
| id           | int\(11\) unsigned    | 否   | \<auto\_increment>  |      | 讲师ID                                 |
| mobile       | char\(11\)            | 否   |                     |      | 手机号                                 |
| email        | varchar\(127\)        | 是   |                     |      | 邮箱地址                               |
| password     | varchar\(255\)        | 否   |                     |      | 密码                                   |
| name         | varchar\(63\)         | 否   |                     |      | 讲师姓名                               |
| intro        | varchar\(1023\)       | 否   |                     |      | 讲师简介                               |
| avatar       | varchar\(1023\)       | 是   |                     |      | 讲师头像                               |
| resume       | varchar\(1023\)       | 是   |                     |      | 讲师简历链接                           |
| division     | tinyint\(4\)          | 是   | 80                  |      | 分成比例，0-100                        |
| sort         | int\(11\)             | 是   | 0                   |      | 排序                                   |
| enable       | tinyint\(1\) unsigned | 否   | 1                   |      | 是否启用，0否1是                       |
| status       | tinyint\(4\)          | 是   | 0                   |      | 讲师状态：审核通过；审核不通过；待审核 |
| update\_time | datetime              | 是   | \<INSERT-TimeStamp> |      | 更新时间                               |
| create\_time | datetime              | 是   | \<INSERT-TimeStamp> |      | 创建时间                               |

**10.表格: edu\_video**

备注: 课程视频

索引:

| 名称             | 类型        | 属性   | 备注 |
| ---------------- | ----------- | ------ | ---- |
| 主索引           | id          | unique |      |
| idx\_chapter\_id | chapter\_id |        |      |
| idx\_course\_id  | course\_id  |        |      |

字段:

| 名称         | 类型                  | 空   | 默认值              | 属性 | 备注                      |
| ------------ | --------------------- | ---- | ------------------- | ---- | ------------------------- |
| id           | int\(11\) unsigned    | 否   | \<auto\_increment>  |      | 视频ID                    |
| course\_id   | int\(11\) unsigned    | 否   |                     |      | 课程ID                    |
| chapter\_id  | int\(11\) unsigned    | 否   |                     |      | 章节ID                    |
| title        | varchar\(63\)         | 否   |                     |      | 视频显示名称              |
| video\_id    | varchar\(63\)         | 否   |                     |      | 云端视频资源              |
| sort         | int\(11\)             | 是   | 0                   |      | 排序字段                  |
| play\_count  | int\(11\) unsigned    | 是   | 0                   |      | 播放次数                  |
| free         | tinyint\(1\) unsigned | 是   | 1                   |      | 是否可以试听：0免费 1收费 |
| duration     | varchar\(15\)         | 是   | 0                   |      | 视频时长（秒）            |
| size         | bigint\(20\) unsigned | 否   | 0                   |      | 视频源文件大小（字节）    |
| update\_time | datetime              | 是   | \<INSERT-TimeStamp> |      | 更新时间                  |
| create\_time | datetime              | 是   | \<INSERT-TimeStamp> |      | 创建时间                  |

**11.表格: edu\_video\_tmp**

备注: 课程视频表（用于存放二次修改的数据）

索引:

| 名称             | 类型        | 属性   | 备注 |
| ---------------- | ----------- | ------ | ---- |
| 主索引           | id          | unique |      |
| idx\_chapter\_id | chapter\_id |        |      |
| idx\_course\_id  | course\_id  |        |      |
| idx\_id          | oid         |        |      |

字段:

| 名称         | 类型                  | 空   | 默认值              | 属性 | 备注                      |
| ------------ | --------------------- | ---- | ------------------- | ---- | ------------------------- |
| id           | bigint\(20\) unsigned | 否   | 0                   |      | 主键\(视频ID\)            |
| oid          | int\(11\) unsigned    | 是   | 0                   |      | 原视频ID                  |
| course\_id   | int\(11\) unsigned    | 否   |                     |      | 课程ID                    |
| chapter\_id  | bigint\(20\) unsigned | 否   |                     |      | 章节ID                    |
| title        | varchar\(63\)         | 否   |                     |      | 视频显示名称              |
| video\_id    | varchar\(63\)         | 否   |                     |      | 云端视频资源              |
| sort         | int\(11\)             | 是   | 0                   |      | 排序字段                  |
| play\_count  | int\(11\) unsigned    | 是   | 0                   |      | 播放次数                  |
| free         | tinyint\(1\) unsigned | 是   | 1                   |      | 是否可以试听：0免费 1收费 |
| duration     | varchar\(15\)         | 是   | 0                   |      | 视频时长（秒）            |
| size         | bigint\(20\) unsigned | 否   | 0                   |      | 视频源文件大小（字节）    |
| update\_time | datetime              | 是   | \<INSERT-TimeStamp> |      | 更新时间                  |
| create\_time | datetime              | 是   | \<INSERT-TimeStamp> |      | 创建时间                  |

**12.表格: hm\_banner**

备注: 首页banner表

索引:

| 名称   | 类型 | 属性   | 备注 |
| ------ | ---- | ------ | ---- |
| 主索引 | id   | unique |      |

字段:

| 名称         | 类型                  | 空   | 默认值              | 属性 | 备注             |
| ------------ | --------------------- | ---- | ------------------- | ---- | ---------------- |
| id           | int\(11\) unsigned    | 否   | \<auto\_increment>  |      | ID               |
| title        | varchar\(63\)         | 是   |                     |      | 标题             |
| image\_url   | varchar\(1023\)       | 否   |                     |      | 图片地址         |
| link\_url    | varchar\(1023\)       | 是   |                     |      | 链接地址         |
| sort         | int\(11\) unsigned    | 否   | 0                   |      | 排序             |
| enable       | tinyint\(1\) unsigned | 否   | 1                   |      | 是否启用，0否1是 |
| update\_time | datetime              | 是   | \<INSERT-TimeStamp> |      | 更新时间         |
| create\_time | datetime              | 是   | \<INSERT-TimeStamp> |      | 创建时间         |

**13.表格: rel\_course\_member**

备注: 课程订阅-学员关系表

索引:

| 名称                | 类型                   | 属性   | 备注                               |
| ------------------- | ---------------------- | ------ | ---------------------------------- |
| 主索引              | id                     | unique |                                    |
| idx\_course\_id     | course\_id             | unique |                                    |
| idx\_member\_course | member\_id, course\_id |        | 联合索引\(member\_id, course\_id\) |
| idx\_member\_id     | member\_id             | unique |                                    |

字段:

| 名称         | 类型               | 空   | 默认值              | 属性 | 备注     |
| ------------ | ------------------ | ---- | ------------------- | ---- | -------- |
| id           | int\(10\) unsigned | 否   | \<auto\_increment>  |      | 主键     |
| course\_id   | int\(10\) unsigned | 否   | 0                   |      | 课程Id   |
| member\_id   | int\(10\) unsigned | 否   | 0                   |      | 学员Id   |
| update\_time | datetime           | 是   | \<INSERT-TimeStamp> |      | 更新时间 |
| create\_time | datetime           | 是   | \<INSERT-TimeStamp> |      | 创建时间 |

**14.表格: stat\_daily**

备注: 网站统计日数据

索引:

| 名称            | 类型 | 属性   | 备注 |
| --------------- | ---- | ------ | ---- |
| 主索引          | id   | unique |      |
| statistics\_day | date |        |      |

字段:

| 名称               | 类型               | 空   | 默认值              | 属性 | 备注       |
| ------------------ | ------------------ | ---- | ------------------- | ---- | ---------- |
| id                 | int\(11\) unsigned | 否   | \<auto\_increment>  |      | 主键       |
| date               | datetime           | 否   |                     |      | 统计日期   |
| visit\_count       | int\(11\) unsigned | 是   | 0                   |      | 访客数量   |
| register\_count    | int\(11\) unsigned | 是   | 0                   |      | 注册人数   |
| login\_count       | int\(11\) unsigned | 是   | 0                   |      | 活跃人数   |
| video\_view\_count | int\(11\) unsigned | 是   | 0                   |      | 视频播放数 |
| course\_buy\_count | int\(11\) unsigned | 是   | 0                   |      | 购买数量   |
| update\_time       | datetime           | 是   | \<INSERT-TimeStamp> |      | 更新时间   |
| create\_time       | datetime           | 是   | \<INSERT-TimeStamp> |      | 创建时间   |

**15.表格: sys\_message**

备注: 消息表

索引:

| 名称        | 类型   | 属性   | 备注 |
| ----------- | ------ | ------ | ---- |
| 主索引      | id     | unique |      |
| idx\_to\_id | to\_id |        |      |

字段:

| 名称         | 类型                  | 空   | 默认值              | 属性 | 备注                          |
| ------------ | --------------------- | ---- | ------------------- | ---- | ----------------------------- |
| id           | int\(11\) unsigned    | 否   | \<auto\_increment>  |      | 消息id                        |
| from\_id     | int\(11\) unsigned    | 否   | 0                   |      | 发送者Id                      |
| from\_role   | tinyint\(4\)          | 否   | 0                   |      | 发送者角色\(管理员、讲师...\) |
| to\_id       | int\(11\) unsigned    | 否   | 0                   |      | 接受者id                      |
| to\_role     | tinyint\(4\) unsigned | 否   | 0                   |      | 接受者角色\(教师、学员...\)   |
| title        | varchar\(127\)        | 是   |                     |      | 消息标题                      |
| content      | varchar\(511\)        | 否   |                     |      | 消息内容                      |
| has\_read    | tinyint\(1\) unsigned | 是   | 0                   |      | 是否已读\(0未读 1已读\)       |
| update\_time | datetime              | 是   | \<INSERT-TimeStamp> |      | 更新时间                      |
| create\_time | datetime              | 是   | \<INSERT-TimeStamp> |      | 创建时间                      |

**16.表格: t\_order**

备注: 订单表

索引:

| 名称                        | 类型                   | 属性   | 备注 |
| --------------------------- | ---------------------- | ------ | ---- |
| 主索引                      | id                     | unique |      |
| idx\_course\_id             | course\_id             |        |      |
| idx\_member\_id             | member\_id             |        |      |
| idx\_member\_id\_course\_id | member\_id, course\_id |        |      |
| ux\_order\_no               | order\_no              | unique |      |

字段:

| 名称             | 类型                  | 空   | 默认值              | 属性 | 备注                                   |
| ---------------- | --------------------- | ---- | ------------------- | ---- | -------------------------------------- |
| id               | int\(11\) unsigned    | 否   | \<auto\_increment>  |      |                                        |
| order\_no        | varchar\(18\)         | 否   |                     |      | 订单号\(datetime+unsigned int\)        |
| course\_id       | int\(11\) unsigned    | 否   |                     |      | 课程id                                 |
| member\_id       | int\(11\) unsigned    | 否   |                     |      | 会员id                                 |
| total\_fee       | double\(10,2\)        | 是   | 0.01                |      | 订单金额（分）                         |
| pay\_type        | tinyint\(4\) unsigned | 是   | 0                   |      | 支付类型（0 未支付 1：微信 2：支付宝） |
| transaction\_num | varchar\(31\)         | 是   |                     |      | 交易流水号                             |
| pay\_time        | datetime              | 是   | \<空>               |      | 支付完成时间                           |
| update\_time     | datetime              | 是   | \<INSERT-TimeStamp> |      | 更新时间                               |
| create\_time     | datetime              | 是   | \<INSERT-TimeStamp> |      | 创建时间                               |

**17.表格: uctr\_member**

备注: 会员表

索引:

| 名称         | 类型     | 属性   | 备注 |
| ------------ | -------- | ------ | ---- |
| 主索引       | id       | unique |      |
| uk\_mobile   | mobile   | unique |      |
| uk\_nickname | nickname | unique |      |

字段:

| 名称         | 类型                  | 空   | 默认值              | 属性 | 备注             |
| ------------ | --------------------- | ---- | ------------------- | ---- | ---------------- |
| id           | int\(11\) unsigned    | 否   | \<auto\_increment>  |      | 会员id           |
| mobile       | char\(11\)            | 否   |                     |      | 手机号           |
| email        | varchar\(127\)        | 是   |                     |      | 邮箱地址         |
| password     | varchar\(255\)        | 否   |                     |      | 密码             |
| nickname     | varchar\(31\)         | 是   |                     |      | 昵称             |
| sex          | tinyint\(4\) unsigned | 是   | 0                   |      | 性别 1 女，2 男  |
| age          | tinyint\(3\) unsigned | 是   | 0                   |      | 年龄             |
| avatar       | varchar\(1023\)       | 是   |                     |      | 用户头像         |
| sign         | varchar\(127\)        | 是   |                     |      | 用户签名         |
| enable       | tinyint\(1\)          | 是   | 1                   |      | 是否启用，0否1是 |
| update\_time | datetime              | 是   | \<INSERT-TimeStamp> |      | 更新时间         |
| create\_time | datetime              | 是   | \<INSERT-TimeStamp> |      | 创建时间         |

**18.表格: event**

备注: 用户事件记录表

索引:

| 名称                | 类型                   | 属性   | 备注                                      |
| ------------------- | ---------------------- | ------ | ----------------------------------------- |
| 主索引              | Id               | unique | 用户唯一标识触发事件                        |
| idx_event_type      | eventType              |        | 事件类型索引（如 "play", "pause" 等）      |
| idx_timestamp       | timestamp              |        | 时间戳索引，便于按时间顺序查询事件        |
| idx_course_id       | courseId               |        | 课程ID索引，关联课程与事件                  |

字段:

| 名称             | 类型       | 空   | 默认值              | 属性 | 备注                   |
| ---------------- | ---------- | ---- | ------------------- | ---- | ---------------------- |
| objectId         | String     | 否   | \<auto\_increment>  |      | 用户ID，唯一标识触发事件的用户  |
| timestamp        | Date       | 否   | \<INSERT-TimeStamp> |      | 事件发生的时间戳           |
| videoPosition    | Number     | 否   | 0                   |      | 视频中的位置（单位：秒）    |
| eventType        | String     | 否   |                     |      | 事件类型（如 "play" 等）    |
| originalPosition | Number     | 是   | 0                   |      | 仅在事件类型为 "skip" 时有效，表示跳跃前的视频位置（单位：秒） |
| courseId         | String     | 否   | 0                   |      | 课程ID，标识事件发生的课程  |
## 七、后端接口

### POST 创建Banner

POST /api/admin/banner/create

> Body 请求参数
```yaml
file: ""

```

#### 请求参数

| 名称     | 位置  | 类型           | 必选 | 说明             |
| -------- | ----- | -------------- | ---- | ---------------- |
| enable   | query | boolean        | 否   | 是否启用，0否1是 |
| id       | query | integer(int32) | 否   | ID               |
| imageUrl | query | string         | 否   | 图片地址         |
| linkUrl  | query | string         | 否   | 链接地址         |
| sort     | query | integer(int32) | 否   | 排序             |
| title    | query | string         | 否   | 标题             |
| body     | body  | object         | 否   | none             |
| » file   | body  | string(binary) | 否   | file             |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 删除Banner

POST /api/admin/banner/delete/{bannerId}

#### 请求参数

| 名称     | 位置 | 类型           | 必选 | 说明     |
| -------- | ---- | -------------- | ---- | -------- |
| bannerId | path | integer(int32) | 是   | bannerId |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 禁用Banner

POST /api/admin/banner/disable/{bannerId}

#### 请求参数

| 名称     | 位置 | 类型           | 必选 | 说明     |
| -------- | ---- | -------------- | ---- | -------- |
| bannerId | path | integer(int32) | 是   | bannerId |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 启用Banner

POST /api/admin/banner/enable/{bannerId}

#### 请求参数

| 名称     | 位置 | 类型           | 必选 | 说明     |
| -------- | ---- | -------------- | ---- | -------- |
| bannerId | path | integer(int32) | 是   | bannerId |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 搜索Banner

POST /api/admin/banner/list

> Body 请求参数

```json
{
  "current": 0,
  "enable": true,
  "pageSize": 0,
  "title": "string"
}
```

#### 请求参数

| 名称 | 位置 | 类型 | 必选 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| body | body | any  | 否   | none |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 刷新Banner缓存

POST /api/admin/banner/refresh

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 修改Banner信息

POST /api/admin/banner/update

> Body 请求参数

```yaml
file: ""

```

#### 请求参数

| 名称     | 位置  | 类型           | 必选 | 说明             |
| -------- | ----- | -------------- | ---- | ---------------- |
| enable   | query | boolean        | 否   | 是否启用，0否1是 |
| id       | query | integer(int32) | 否   | ID               |
| imageUrl | query | string         | 否   | 图片地址         |
| linkUrl  | query | string         | 否   | 链接地址         |
| sort     | query | integer(int32) | 否   | 排序             |
| title    | query | string         | 否   | 标题             |
| body     | body  | object         | 否   | none             |
| » file   | body  | string(binary) | 否   | file             |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构

## 管理员端章节接口



### GET 获取章节

GET /api/admin/chapter/list/{courseId}

#### 请求参数

| 名称     | 位置 | 类型           | 必选 | 说明     |
| -------- | ---- | -------------- | ---- | -------- |
| courseId | path | integer(int32) | 是   | courseId |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### GET 获取章节

GET /api/admin/chapter/tmp/list/{courseId}

#### 请求参数

| 名称     | 位置 | 类型           | 必选 | 说明     |
| -------- | ---- | -------------- | ---- | -------- |
| courseId | path | integer(int32) | 是   | courseId |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构

## 后台课程接口



### POST 删除课程

POST /api/admin/course/delete/{id}

#### 请求参数

| 名称 | 位置 | 类型           | 必选 | 说明 |
| ---- | ---- | -------------- | ---- | ---- |
| id   | path | integer(int32) | 是   | id   |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 下架课程

POST /api/admin/course/disable/{id}

#### 请求参数

| 名称 | 位置 | 类型           | 必选 | 说明 |
| ---- | ---- | -------------- | ---- | ---- |
| id   | path | integer(int32) | 是   | id   |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 上架课程

POST /api/admin/course/enable/{id}

#### 请求参数

| 名称 | 位置 | 类型           | 必选 | 说明 |
| ---- | ---- | -------------- | ---- | ---- |
| id   | path | integer(int32) | 是   | id   |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### GET 获取课程详细的信息

GET /api/admin/course/info/{id}

#### 请求参数

| 名称 | 位置 | 类型           | 必选 | 说明 |
| ---- | ---- | -------------- | ---- | ---- |
| id   | path | integer(int32) | 是   | id   |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 搜索课程

POST /api/admin/course/list

> Body 请求参数

```json
{
  "current": 0,
  "enable": true,
  "free": true,
  "pageSize": 0,
  "status": "DRAFT",
  "subjectId": 0,
  "teacherId": 0,
  "title": "string"
}
```

#### 请求参数

| 名称 | 位置 | 类型 | 必选 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| body | body | any  | 否   | none |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 通过审核

POST /api/admin/course/pass/{id}

#### 请求参数

| 名称 | 位置 | 类型           | 必选 | 说明 |
| ---- | ---- | -------------- | ---- | ---- |
| id   | path | integer(int32) | 是   | id   |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 驳回审核

POST /api/admin/course/reject

#### 请求参数

| 名称    | 位置  | 类型           | 必选 | 说明    |
| ------- | ----- | -------------- | ---- | ------- |
| id      | query | integer(int32) | 是   | id      |
| remarks | query | string         | 是   | remarks |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 修改课程信息

POST /api/admin/course/update

> Body 请求参数

```yaml
file: ""

```

#### 请求参数

| 名称                | 位置  | 类型           | 必选 | 说明                              |
| ------------------- | ----- | -------------- | ---- | --------------------------------- |
| buyCount            | query | integer(int32) | 否   | 销售数量                          |
| cover               | query | string         | 否   | 课程封面图片路径                  |
| description         | query | string         | 否   | 课程描述                          |
| enable              | query | boolean        | 否   | 上架下架，0下架 1上架             |
| id                  | query | integer(int32) | 否   | 课程ID                            |
| lessonNum           | query | integer(int32) | 否   | 总课时                            |
| price               | query | number(double) | 否   | 课程销售价格，设置为0则可免费观看 |
| remarks             | query | string         | 否   | 备注                              |
| sort                | query | integer(int32) | 否   | 排序                              |
| status              | query | string         | 否   | 课程状态，草稿 审核 发表          |
| subjectId           | query | integer(int32) | 否   | 课程专业ID                        |
| subjectParent.id    | query | integer(int32) | 否   | none                              |
| subjectParent.title | query | string         | 否   | 标题                              |
| teacherId           | query | integer(int32) | 否   | 课程讲师ID                        |
| teacherName         | query | string         | 否   | none                              |
| title               | query | string         | 否   | 课程标题                          |
| viewCount           | query | integer(int32) | 否   | 浏览数量                          |
| body                | body  | object         | 否   | none                              |
| » file              | body  | string(binary) | 否   | file                              |

##### 枚举值

| 属性   | 值              |
| ------ | --------------- |
| status | DRAFT           |
| status | PUBLISH         |
| status | TURN_DOWN       |
| status | AUDITING        |
| status | FIRST_AUDITING  |
| status | SECOND_AUDITING |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 上传图片

POST /api/admin/course/upload/pic

> Body 请求参数

```yaml
file: ""

```

#### 请求参数

| 名称   | 位置 | 类型           | 必选 | 说明 |
| ------ | ---- | -------------- | ---- | ---- |
| body   | body | object         | 否   | none |
| » file | body | string(binary) | 是   | file |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构

## 后台学员接口



### POST 创建学员

POST /api/admin/member/create

> Body 请求参数

```yaml
file: ""

```

#### 请求参数

| 名称     | 位置  | 类型           | 必选 | 说明                   |
| -------- | ----- | -------------- | ---- | ---------------------- |
| age      | query | integer(int32) | 否   | none                   |
| avatar   | query | string         | 否   | none                   |
| email    | query | string         | 否   | 邮箱地址               |
| enable   | query | boolean        | 否   | none                   |
| id       | query | integer(int32) | 否   | none                   |
| mobile   | query | string         | 否   | 手机号                 |
| nickname | query | string         | 否   | none                   |
| password | query | string         | 否   | 密码                   |
| sex      | query | string         | 否   | 性别 0 保密 1 女，2 男 |
| sign     | query | string         | 否   | none                   |
| token    | query | string         | 否   | none                   |
| body     | body  | object         | 否   | none                   |
| » file   | body  | string(binary) | 否   | file                   |

##### 枚举值

| 属性 | 值     |
| ---- | ------ |
| sex  | SECRET |
| sex  | FEMALE |
| sex  | MALE   |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 删除学员

POST /api/admin/member/delete/{userId}

#### 请求参数

| 名称   | 位置 | 类型           | 必选 | 说明   |
| ------ | ---- | -------------- | ---- | ------ |
| userId | path | integer(int32) | 是   | userId |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 禁用学员

POST /api/admin/member/disable/{userId}

#### 请求参数

| 名称   | 位置 | 类型           | 必选 | 说明   |
| ------ | ---- | -------------- | ---- | ------ |
| userId | path | integer(int32) | 是   | userId |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 启用学员

POST /api/admin/member/enable/{userId}

#### 请求参数

| 名称   | 位置 | 类型           | 必选 | 说明   |
| ------ | ---- | -------------- | ---- | ------ |
| userId | path | integer(int32) | 是   | userId |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### GET 获取学员详细的信息

GET /api/admin/member/info/{userId}

#### 请求参数

| 名称   | 位置 | 类型           | 必选 | 说明   |
| ------ | ---- | -------------- | ---- | ------ |
| userId | path | integer(int32) | 是   | userId |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 搜索学员

POST /api/admin/member/list

> Body 请求参数

```json
{
  "beginCreate": "2019-08-24T14:15:22Z",
  "current": 0,
  "enable": true,
  "endCreate": "2019-08-24T14:15:22Z",
  "mobile": "string",
  "nickname": "string",
  "pageSize": 0
}
```

#### 请求参数

| 名称 | 位置 | 类型 | 必选 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| body | body | any  | 否   | none |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 管理员权限直接修改密码

POST /api/admin/member/update/password

> Body 请求参数

```json
{
  "newPassword": "string",
  "userId": 0
}
```

#### 请求参数

| 名称 | 位置 | 类型 | 必选 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| body | body | any  | 否   | none |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 修改学员信息

POST /api/admin/member/update/profile

> Body 请求参数

```yaml
file: ""

```

#### 请求参数

| 名称       | 位置  | 类型              | 必选 | 说明                   |
| ---------- | ----- | ----------------- | ---- | ---------------------- |
| age        | query | integer(int32)    | 否   | none                   |
| avatar     | query | string            | 否   | none                   |
| createTime | query | string(date-time) | 否   | none                   |
| email      | query | string            | 否   | 邮箱地址               |
| enable     | query | boolean           | 否   | none                   |
| id         | query | integer(int32)    | 否   | none                   |
| mobile     | query | string            | 否   | 手机号                 |
| nickname   | query | string            | 否   | none                   |
| sex        | query | string            | 否   | 性别 0 保密 1 女，2 男 |
| sign       | query | string            | 否   | none                   |
| token      | query | string            | 否   | none                   |
| body       | body  | object            | 否   | none                   |
| » file     | body  | string(binary)    | 否   | file                   |

##### 枚举值

| 属性 | 值     |
| ---- | ------ |
| sex  | SECRET |
| sex  | FEMALE |
| sex  | MALE   |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构

## 后台首页订单接口



### POST 搜索订单

POST /api/admin/order/list

> Body 请求参数

```json
{
  "beginCreate": "2019-08-24T14:15:22Z",
  "current": 0,
  "endCreate": "2019-08-24T14:15:22Z",
  "memberId": 0,
  "orderNo": "string",
  "pageSize": 0,
  "payType": "NONE"
}
```

#### 请求参数

| 名称 | 位置 | 类型 | 必选 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| body | body | any  | 否   | none |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构

## 后台角色接口



### GET 根据获取所有角色

GET /api/admin/role/list/all/{enable}

#### 请求参数

| 名称   | 位置 | 类型    | 必选 | 说明   |
| ------ | ---- | ------- | ---- | ------ |
| enable | path | boolean | 是   | enable |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构

## 后台数据统计接口



### GET 获取平台数据统计

GET /api/admin/stat/get/common

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 获取平台每日数据统计

POST /api/admin/stat/get/daily

#### 请求参数

| 名称  | 位置  | 类型              | 必选 | 说明  |
| ----- | ----- | ----------------- | ---- | ----- |
| end   | query | string(date-time) | 是   | end   |
| start | query | string(date-time) | 是   | start |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构

## 后台课程科目(分类)控制器接口



### POST 创建分类

POST /api/admin/subject/create

> Body 请求参数

```json
{
  "children": [
    {
      "children": [
        {
          "children": [
            {}
          ],
          "enable": true,
          "id": 0,
          "parentId": 0,
          "sort": 0,
          "title": "string"
        }
      ],
      "enable": true,
      "id": 0,
      "parentId": 0,
      "sort": 0,
      "title": "string"
    }
  ],
  "enable": true,
  "id": 0,
  "parentId": 0,
  "sort": 0,
  "title": "string"
}
```

#### 请求参数

| 名称 | 位置 | 类型                                            | 必选 | 中文名             | 说明 |
| ---- | ---- | ----------------------------------------------- | ---- | ------------------ | ---- |
| body | body | [EduSubjectDetailVO](#schemaedusubjectdetailvo) | 否   | EduSubjectDetailVO | none |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 删除分类

POST /api/admin/subject/delete/{id}

#### 请求参数

| 名称 | 位置 | 类型           | 必选 | 中文名 | 说明 |
| ---- | ---- | -------------- | ---- | ------ | ---- |
| id   | path | integer(int32) | 是   |        | id   |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 禁用分类

POST /api/admin/subject/disable/{id}

#### 请求参数

| 名称 | 位置 | 类型           | 必选 | 中文名 | 说明 |
| ---- | ---- | -------------- | ---- | ------ | ---- |
| id   | path | integer(int32) | 是   |        | id   |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 启用分类

POST /api/admin/subject/enable/{id}

#### 请求参数

| 名称 | 位置 | 类型           | 必选 | 中文名 | 说明 |
| ---- | ---- | -------------- | ---- | ------ | ---- |
| id   | path | integer(int32) | 是   |        | id   |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### GET 获取所有分类

GET /api/admin/subject/get

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 搜索分类

POST /api/admin/subject/list

> Body 请求参数

```json
{
  "current": 0,
  "enable": true,
  "pageSize": 0,
  "parentId": 0,
  "title": "string"
}
```

#### 请求参数

| 名称 | 位置 | 类型 | 必选 | 中文名 | 说明 |
| ---- | ---- | ---- | ---- | ------ | ---- |
| body | body | any  | 否   |        | none |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 刷新分类缓存

POST /api/admin/subject/refresh

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 修改分类信息

POST /api/admin/subject/update

> Body 请求参数

```json
{
  "children": [
    {
      "children": [
        {
          "children": [
            {}
          ],
          "enable": true,
          "id": 0,
          "parentId": 0,
          "sort": 0,
          "title": "string"
        }
      ],
      "enable": true,
      "id": 0,
      "parentId": 0,
      "sort": 0,
      "title": "string"
    }
  ],
  "enable": true,
  "id": 0,
  "parentId": 0,
  "sort": 0,
  "title": "string"
}
```

#### 请求参数

| 名称 | 位置 | 类型                                            | 必选 | 中文名             | 说明 |
| ---- | ---- | ----------------------------------------------- | ---- | ------------------ | ---- |
| body | body | [EduSubjectDetailVO](#schemaedusubjectdetailvo) | 否   | EduSubjectDetailVO | none |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构

## 后台讲师接口



### POST 创建讲师

POST /api/admin/teacher/create

> Body 请求参数

```yaml
file: ""
resume: ""

```

#### 请求参数

| 名称     | 位置  | 类型           | 必选 | 中文名 | 说明                                   |
| -------- | ----- | -------------- | ---- | ------ | -------------------------------------- |
| avatar   | query | string         | 否   |        | 讲师头像                               |
| email    | query | string         | 否   |        | 邮箱地址                               |
| enable   | query | boolean        | 否   |        | 是否启用，0否1是                       |
| id       | query | integer(int32) | 否   |        | none                                   |
| intro    | query | string         | 否   |        | 讲师简介                               |
| mobile   | query | string         | 否   |        | 手机号                                 |
| name     | query | string         | 否   |        | 讲师名称                               |
| password | query | string         | 否   |        | 密码                                   |
| resume   | query | string         | 否   |        | 讲师简历                               |
| sort     | query | integer(int32) | 否   |        | 排序                                   |
| status   | query | string         | 否   |        | 讲师状态：审核通过；审核不通过；待审核 |
| token    | query | string         | 否   |        | none                                   |
| body     | body  | object         | 否   |        | none                                   |
| » file   | body  | string(binary) | 否   |        | file                                   |
| » resume | body  | string(binary) | 否   |        | resume                                 |

##### 枚举值

| 属性   | 值       |
| ------ | -------- |
| status | PASS     |
| status | AUDITING |
| status | NOT_PASS |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 删除讲师

POST /api/admin/teacher/delete/{userId}

#### 请求参数

| 名称   | 位置 | 类型           | 必选 | 中文名 | 说明   |
| ------ | ---- | -------------- | ---- | ------ | ------ |
| userId | path | integer(int32) | 是   |        | userId |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 禁用讲师

POST /api/admin/teacher/disable/{userId}

#### 请求参数

| 名称   | 位置 | 类型           | 必选 | 中文名 | 说明   |
| ------ | ---- | -------------- | ---- | ------ | ------ |
| userId | path | integer(int32) | 是   |        | userId |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 启用讲师

POST /api/admin/teacher/enable/{userId}

#### 请求参数

| 名称   | 位置 | 类型           | 必选 | 中文名 | 说明   |
| ------ | ---- | -------------- | ---- | ------ | ------ |
| userId | path | integer(int32) | 是   |        | userId |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### GET 获取讲师详细的信息

GET /api/admin/teacher/info/{userId}

#### 请求参数

| 名称   | 位置 | 类型           | 必选 | 中文名 | 说明   |
| ------ | ---- | -------------- | ---- | ------ | ------ |
| userId | path | integer(int32) | 是   |        | userId |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 搜索讲师

POST /api/admin/teacher/list

> Body 请求参数

```json
{
  "current": 0,
  "enable": true,
  "mobile": "string",
  "name": "string",
  "pageSize": 0,
  "status": "PASS"
}
```

#### 请求参数

| 名称 | 位置 | 类型 | 必选 | 中文名 | 说明 |
| ---- | ---- | ---- | ---- | ------ | ---- |
| body | body | any  | 否   |        | none |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 通过审核

POST /api/admin/teacher/pass/{userId}

#### 请求参数

| 名称   | 位置 | 类型           | 必选 | 中文名 | 说明   |
| ------ | ---- | -------------- | ---- | ------ | ------ |
| userId | path | integer(int32) | 是   |        | userId |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 管理员权限直接修改密码

POST /api/admin/teacher/update/password

> Body 请求参数

```json
{
  "newPassword": "string",
  "userId": 0
}
```

#### 请求参数

| 名称 | 位置 | 类型 | 必选 | 中文名 | 说明 |
| ---- | ---- | ---- | ---- | ------ | ---- |
| body | body | any  | 否   |        | none |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 修改讲师信息

POST /api/admin/teacher/update/profile

> Body 请求参数

```yaml
file: ""
resume: ""

```

#### 请求参数

| 名称     | 位置  | 类型           | 必选 | 中文名 | 说明                                   |
| -------- | ----- | -------------- | ---- | ------ | -------------------------------------- |
| avatar   | query | string         | 否   |        | 讲师头像                               |
| division | query | integer(int32) | 否   |        | 分成比例，0-100                        |
| email    | query | string         | 否   |        | 邮箱地址                               |
| enable   | query | boolean        | 否   |        | 是否启用，0否1是                       |
| id       | query | integer(int32) | 否   |        | none                                   |
| intro    | query | string         | 否   |        | 讲师简介                               |
| mobile   | query | string         | 否   |        | 手机号                                 |
| name     | query | string         | 否   |        | 讲师姓名                               |
| resume   | query | string         | 否   |        | 讲师简历                               |
| sort     | query | integer(int32) | 否   |        | 排序                                   |
| status   | query | string         | 否   |        | 讲师状态：审核通过；审核不通过；待审核 |
| token    | query | string         | 否   |        | none                                   |
| body     | body  | object         | 否   |        | none                                   |
| » file   | body  | string(binary) | 否   |        | file                                   |
| » resume | body  | string(binary) | 否   |        | resume                                 |

##### 枚举值

| 属性   | 值       |
| ------ | -------- |
| status | PASS     |
| status | AUDITING |
| status | NOT_PASS |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构

## 后台用户接口



### POST 创建用户

POST /api/admin/user/create

> Body 请求参数

```json
{
  "avatar": "string",
  "enable": true,
  "id": 0,
  "mark": "string",
  "nickname": "string",
  "password": "string",
  "roleId": 0,
  "sign": "string",
  "token": "string",
  "username": "string"
}
```

#### 请求参数

| 名称 | 位置 | 类型                                  | 必选 | 中文名        | 说明 |
| ---- | ---- | ------------------------------------- | ---- | ------------- | ---- |
| body | body | [AclUserDetail](#schemaacluserdetail) | 否   | AclUserDetail | none |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 删除用户

POST /api/admin/user/delete/{userId}

#### 请求参数

| 名称   | 位置 | 类型           | 必选 | 中文名 | 说明   |
| ------ | ---- | -------------- | ---- | ------ | ------ |
| userId | path | integer(int32) | 是   |        | userId |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 禁用用户

POST /api/admin/user/disable/{userId}

#### 请求参数

| 名称   | 位置 | 类型           | 必选 | 中文名 | 说明   |
| ------ | ---- | -------------- | ---- | ------ | ------ |
| userId | path | integer(int32) | 是   |        | userId |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 启用用户

POST /api/admin/user/enable/{userId}

#### 请求参数

| 名称   | 位置 | 类型           | 必选 | 中文名 | 说明   |
| ------ | ---- | -------------- | ---- | ------ | ------ |
| userId | path | integer(int32) | 是   |        | userId |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### GET 获取用户信息

GET /api/admin/user/info

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 搜索用户

POST /api/admin/user/list

> Body 请求参数

```json
{
  "current": 0,
  "enable": true,
  "pageSize": 0,
  "roleId": 0,
  "username": "string"
}
```

#### 请求参数

| 名称 | 位置 | 类型 | 必选 | 中文名 | 说明 |
| ---- | ---- | ---- | ---- | ------ | ---- |
| body | body | any  | 否   |        | none |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 登录

POST /api/admin/user/login

> Body 请求参数

```json
{
  "password": "string",
  "username": "string"
}
```

#### 请求参数

| 名称 | 位置 | 类型                            | 必选 | 中文名     | 说明 |
| ---- | ---- | ------------------------------- | ---- | ---------- | ---- |
| body | body | [LoginParam](#schemaloginparam) | 否   | LoginParam | none |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 登出

POST /api/admin/user/logout

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 修改头像

POST /api/admin/user/update/avatar

> Body 请求参数

```yaml
file: ""

```

#### 请求参数

| 名称   | 位置 | 类型           | 必选 | 中文名 | 说明 |
| ------ | ---- | -------------- | ---- | ------ | ---- |
| body   | body | object         | 否   |        | none |
| » file | body | string(binary) | 是   |        | file |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 修改密码

POST /api/admin/user/update/password

> Body 请求参数

```json
{
  "confirmNewPassword": "string",
  "newPassword": "string",
  "oldPassword": "string"
}
```

#### 请求参数

| 名称 | 位置 | 类型 | 必选 | 中文名 | 说明 |
| ---- | ---- | ---- | ---- | ------ | ---- |
| body | body | any  | 否   |        | none |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 管理员权限直接修改密码

POST /api/admin/user/update/password/admin

> Body 请求参数

```json
{
  "newPassword": "string",
  "userId": 0
}
```

#### 请求参数

| 名称 | 位置 | 类型 | 必选 | 中文名 | 说明 |
| ---- | ---- | ---- | ---- | ------ | ---- |
| body | body | any  | 否   |        | none |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 修改用户信息

POST /api/admin/user/update/profile

> Body 请求参数

```json
{
  "avatar": "string",
  "enable": true,
  "id": 0,
  "mark": "string",
  "nickname": "string",
  "roleId": 0,
  "sign": "string",
  "token": "string",
  "username": "string"
}
```

#### 请求参数

| 名称 | 位置 | 类型                          | 必选 | 中文名    | 说明 |
| ---- | ---- | ----------------------------- | ---- | --------- | ---- |
| body | body | [AclUserVO](#schemaacluservo) | 否   | AclUserVO | none |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 管理员权限修改用户信息

POST /api/admin/user/update/profile/admin

> Body 请求参数

```json
{
  "avatar": "string",
  "enable": true,
  "id": 0,
  "mark": "string",
  "nickname": "string",
  "roleId": 0,
  "sign": "string",
  "token": "string",
  "username": "string"
}
```

#### 请求参数

| 名称 | 位置 | 类型                          | 必选 | 中文名    | 说明 |
| ---- | ---- | ----------------------------- | ---- | --------- | ---- |
| body | body | [AclUserVO](#schemaacluservo) | 否   | AclUserVO | none |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构

## 管理员端视频接口



### GET 获取视频播放凭证

GET /api/admin/video/auth/{videoId}

#### 请求参数

| 名称    | 位置 | 类型   | 必选 | 中文名 | 说明    |
| ------- | ---- | ------ | ---- | ------ | ------- |
| videoId | path | string | 是   |        | videoId |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### GET 获取章节视频

GET /api/admin/video/list/{chapterId}

#### 请求参数

| 名称      | 位置 | 类型           | 必选 | 中文名 | 说明      |
| --------- | ---- | -------------- | ---- | ------ | --------- |
| chapterId | path | integer(int32) | 是   |        | chapterId |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### GET 获取章节视频

GET /api/admin/video/tmp/list/{chapterId}

#### 请求参数

| 名称      | 位置 | 类型           | 必选 | 中文名 | 说明      |
| --------- | ---- | -------------- | ---- | ------ | --------- |
| chapterId | path | integer(int64) | 是   |        | chapterId |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构

## 前台学员接口



### GET 获取登录用户信息

GET /api/app/member/info

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 登录

POST /api/app/member/login

> Body 请求参数

```json
{
  "password": "string",
  "username": "string"
}
```

#### 请求参数

| 名称 | 位置 | 类型                            | 必选 | 中文名     | 说明 |
| ---- | ---- | ------------------------------- | ---- | ---------- | ---- |
| body | body | [LoginParam](#schemaloginparam) | 否   | LoginParam | none |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 登出

POST /api/app/member/logout

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 注册

POST /api/app/member/register

> Body 请求参数

```json
{
  "confirmPassword": "string",
  "password": "string",
  "username": "string"
}
```

#### 请求参数

| 名称 | 位置 | 类型                                  | 必选 | 中文名        | 说明 |
| ---- | ---- | ------------------------------------- | ---- | ------------- | ---- |
| body | body | [RegisterParam](#schemaregisterparam) | 否   | RegisterParam | none |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 修改头像

POST /api/app/member/update/avatar

> Body 请求参数

```yaml
file: ""

```

#### 请求参数

| 名称   | 位置 | 类型           | 必选 | 中文名 | 说明 |
| ------ | ---- | -------------- | ---- | ------ | ---- |
| body   | body | object         | 否   |        | none |
| » file | body | string(binary) | 是   |        | file |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 修改密码

POST /api/app/member/update/password

> Body 请求参数

```json
{
  "confirmNewPassword": "string",
  "newPassword": "string",
  "oldPassword": "string"
}
```

#### 请求参数

| 名称 | 位置 | 类型 | 必选 | 中文名 | 说明 |
| ---- | ---- | ---- | ---- | ------ | ---- |
| body | body | any  | 否   |        | none |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 修改学员信息

POST /api/app/member/update/profile

> Body 请求参数

```json
{
  "age": 0,
  "avatar": "string",
  "createTime": "2019-08-24T14:15:22Z",
  "email": "string",
  "enable": true,
  "id": 0,
  "mobile": "string",
  "nickname": "string",
  "sex": "SECRET",
  "sign": "string",
  "token": "string"
}
```

#### 请求参数

| 名称 | 位置 | 类型                                            | 必选 | 中文名             | 说明 |
| ---- | ---- | ----------------------------------------------- | ---- | ------------------ | ---- |
| body | body | [UctrMemberDetailVO](#schemauctrmemberdetailvo) | 否   | UctrMemberDetailVO | none |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构

## 前台内容接口



### POST 创建课程支付订单

POST /api/app/pub/content/course/pay/create

> Body 请求参数

```json
{
  "courseId": 0,
  "createTime": "2019-08-24T14:15:22Z",
  "id": 0,
  "memberId": 0,
  "orderNo": "string",
  "payTime": "2019-08-24T14:15:22Z",
  "payType": "NONE",
  "totalFee": 0.1,
  "transactionNum": "string"
}
```

#### 请求参数

| 名称 | 位置 | 类型                        | 必选 | 中文名   | 说明 |
| ---- | ---- | --------------------------- | ---- | -------- | ---- |
| body | body | [TOrderVO](#schematordervo) | 否   | TOrderVO | none |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 课程支付成功

POST /api/app/pub/content/course/pay/succeed/{orderNo}

#### 请求参数

| 名称    | 位置 | 类型   | 必选 | 中文名 | 说明    |
| ------- | ---- | ------ | ---- | ------ | ------- |
| orderNo | path | string | 是   |        | orderNo |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### GET 获取所有banner

GET /api/app/pub/content/get/banners

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### GET 获取课程章节

GET /api/app/pub/content/get/chapters/{courseId}

#### 请求参数

| 名称     | 位置 | 类型           | 必选 | 中文名 | 说明     |
| -------- | ---- | -------------- | ---- | ------ | -------- |
| courseId | path | integer(int32) | 是   |        | courseId |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 列出课程评论

POST /api/app/pub/content/get/course/comment

> Body 请求参数

```json
{
  "courseId": 0,
  "current": 0,
  "pageSize": 0,
  "status": true
}
```

#### 请求参数

| 名称 | 位置 | 类型 | 必选 | 中文名 | 说明 |
| ---- | ---- | ---- | ---- | ------ | ---- |
| body | body | any  | 否   |        | none |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### GET 判断学员是否已经订阅课程

GET /api/app/pub/content/get/course/isbuy/{id}

#### 请求参数

| 名称 | 位置 | 类型           | 必选 | 中文名 | 说明 |
| ---- | ---- | -------------- | ---- | ------ | ---- |
| id   | path | integer(int32) | 是   |        | id   |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 获取课程订阅订单列表

POST /api/app/pub/content/get/course/orders

> Body 请求参数

```json
{
  "beginCreate": "2019-08-24T14:15:22Z",
  "current": 0,
  "endCreate": "2019-08-24T14:15:22Z",
  "memberId": 0,
  "orderNo": "string",
  "pageSize": 0,
  "payType": "NONE"
}
```

#### 请求参数

| 名称 | 位置 | 类型 | 必选 | 中文名 | 说明 |
| ---- | ---- | ---- | ---- | ------ | ---- |
| body | body | any  | 否   |        | none |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 获取所有课程

POST /api/app/pub/content/get/courses

> Body 请求参数

```json
{
  "current": 0,
  "enable": true,
  "free": true,
  "pageSize": 0,
  "status": "DRAFT",
  "subjectId": 0,
  "teacherId": 0,
  "title": "string"
}
```

#### 请求参数

| 名称 | 位置 | 类型 | 必选 | 中文名 | 说明 |
| ---- | ---- | ---- | ---- | ------ | ---- |
| body | body | any  | 否   |        | none |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### GET 获取课程详情

GET /api/app/pub/content/get/courses/{id}

#### 请求参数

| 名称 | 位置 | 类型           | 必选 | 中文名 | 说明 |
| ---- | ---- | -------------- | ---- | ------ | ---- |
| id   | path | integer(int32) | 是   |        | id   |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### GET 获取分类父分类

GET /api/app/pub/content/get/subject/parent/{id}

#### 请求参数

| 名称 | 位置 | 类型           | 必选 | 中文名 | 说明 |
| ---- | ---- | -------------- | ---- | ------ | ---- |
| id   | path | integer(int32) | 是   |        | id   |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### GET 获取所有分类

GET /api/app/pub/content/get/subjects

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### GET 获取讲师详情信息

GET /api/app/pub/content/get/teacher/{id}

#### 请求参数

| 名称 | 位置 | 类型           | 必选 | 中文名 | 说明 |
| ---- | ---- | -------------- | ---- | ------ | ---- |
| id   | path | integer(int32) | 是   |        | id   |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 获取视频播放凭证

POST /api/app/pub/content/get/video/auth

#### 请求参数

| 名称          | 位置  | 类型           | 必选 | 中文名 | 说明          |
| ------------- | ----- | -------------- | ---- | ------ | ------------- |
| courseId      | query | integer(int32) | 是   |        | courseId      |
| videoId       | query | integer(int32) | 是   |        | videoId       |
| videoSourceId | query | string         | 是   |        | videoSourceId |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### GET 获取章节视频

GET /api/app/pub/content/get/videos/{chapterId}

#### 请求参数

| 名称      | 位置 | 类型           | 必选 | 中文名 | 说明      |
| --------- | ---- | -------------- | ---- | ------ | --------- |
| chapterId | path | integer(int32) | 是   |        | chapterId |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 发表课程评论

POST /api/app/pub/content/publish/comment

> Body 请求参数

```json
{
  "content": "string",
  "courseId": 0,
  "courseName": "string",
  "createTime": "2019-08-24T14:15:22Z",
  "id": 0,
  "mark": 0.1,
  "memberAvatar": "string",
  "memberId": 0,
  "memberName": "string",
  "status": true,
  "teacherId": 0
}
```

#### 请求参数

| 名称 | 位置 | 类型                                | 必选 | 中文名       | 说明 |
| ---- | ---- | ----------------------------------- | ---- | ------------ | ---- |
| body | body | [EduCommentVO](#schemaeducommentvo) | 否   | EduCommentVO | none |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 讲师申请

POST /api/app/pub/content/teacher/apply

> Body 请求参数

```yaml
file: ""
resumeFile: ""

```

#### 请求参数

| 名称         | 位置  | 类型           | 必选 | 中文名 | 说明                                   |
| ------------ | ----- | -------------- | ---- | ------ | -------------------------------------- |
| avatar       | query | string         | 否   |        | 讲师头像                               |
| email        | query | string         | 否   |        | 邮箱地址                               |
| enable       | query | boolean        | 否   |        | 是否启用，0否1是                       |
| id           | query | integer(int32) | 否   |        | none                                   |
| intro        | query | string         | 否   |        | 讲师简介                               |
| mobile       | query | string         | 否   |        | 手机号                                 |
| name         | query | string         | 否   |        | 讲师名称                               |
| password     | query | string         | 否   |        | 密码                                   |
| resume       | query | string         | 否   |        | 讲师简历                               |
| sort         | query | integer(int32) | 否   |        | 排序                                   |
| status       | query | string         | 否   |        | 讲师状态：审核通过；审核不通过；待审核 |
| token        | query | string         | 否   |        | none                                   |
| body         | body  | object         | 否   |        | none                                   |
| » file       | body  | string(binary) | 否   |        | file                                   |
| » resumeFile | body  | string(binary) | 否   |        | resumeFile                             |

##### 枚举值

| 属性   | 值       |
| ------ | -------- |
| status | PASS     |
| status | AUDITING |
| status | NOT_PASS |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构

## 讲师端章节接口（二次修改）



### POST 创建章节

POST /api/teacher/chapter/tmp/create

> Body 请求参数

```json
{
  "courseId": 0,
  "id": "string",
  "oid": 0,
  "sort": 0,
  "title": "string"
}
```

#### 请求参数

| 名称 | 位置 | 类型                                      | 必选 | 中文名          | 说明 |
| ---- | ---- | ----------------------------------------- | ---- | --------------- | ---- |
| body | body | [EduChapterTmpVO](#schemaeduchaptertmpvo) | 否   | EduChapterTmpVO | none |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 删除章节

POST /api/teacher/chapter/tmp/delete/{id}

#### 请求参数

| 名称 | 位置 | 类型           | 必选 | 中文名 | 说明 |
| ---- | ---- | -------------- | ---- | ------ | ---- |
| id   | path | integer(int64) | 是   |        | id   |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### GET 获取章节

GET /api/teacher/chapter/tmp/list/{courseId}

#### 请求参数

| 名称     | 位置 | 类型           | 必选 | 中文名 | 说明     |
| -------- | ---- | -------------- | ---- | ------ | -------- |
| courseId | path | integer(int32) | 是   |        | courseId |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 更新章节信息

POST /api/teacher/chapter/tmp/update

> Body 请求参数

```json
{
  "courseId": 0,
  "id": "string",
  "oid": 0,
  "sort": 0,
  "title": "string"
}
```

#### 请求参数

| 名称 | 位置 | 类型                                      | 必选 | 中文名          | 说明 |
| ---- | ---- | ----------------------------------------- | ---- | --------------- | ---- |
| body | body | [EduChapterTmpVO](#schemaeduchaptertmpvo) | 否   | EduChapterTmpVO | none |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构

## 讲师端评论接口



### POST 创建评论

POST /api/teacher/comment/create

> Body 请求参数

```json
{
  "content": "string",
  "courseId": 0,
  "courseName": "string",
  "createTime": "2019-08-24T14:15:22Z",
  "id": 0,
  "mark": 0.1,
  "memberAvatar": "string",
  "memberId": 0,
  "memberName": "string",
  "status": true,
  "teacherId": 0
}
```

#### 请求参数

| 名称 | 位置 | 类型                                | 必选 | 中文名       | 说明 |
| ---- | ---- | ----------------------------------- | ---- | ------------ | ---- |
| body | body | [EduCommentVO](#schemaeducommentvo) | 否   | EduCommentVO | none |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 删除评论

POST /api/teacher/comment/delete/{id}

#### 请求参数

| 名称 | 位置 | 类型           | 必选 | 中文名 | 说明 |
| ---- | ---- | -------------- | ---- | ------ | ---- |
| id   | path | integer(int32) | 是   |        | id   |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 搜索评论

POST /api/teacher/comment/list

> Body 请求参数

```json
{
  "courseId": 0,
  "current": 0,
  "pageSize": 0,
  "status": true
}
```

#### 请求参数

| 名称 | 位置 | 类型 | 必选 | 中文名 | 说明 |
| ---- | ---- | ---- | ---- | ------ | ---- |
| body | body | any  | 否   |        | none |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 更新评论信息

POST /api/teacher/comment/update

> Body 请求参数

```json
{
  "content": "string",
  "courseId": 0,
  "courseName": "string",
  "createTime": "2019-08-24T14:15:22Z",
  "id": 0,
  "mark": 0.1,
  "memberAvatar": "string",
  "memberId": 0,
  "memberName": "string",
  "status": true,
  "teacherId": 0
}
```

#### 请求参数

| 名称 | 位置 | 类型                                | 必选 | 中文名       | 说明 |
| ---- | ---- | ----------------------------------- | ---- | ------------ | ---- |
| body | body | [EduCommentVO](#schemaeducommentvo) | 否   | EduCommentVO | none |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构

## 讲师端课程接口



### POST 创建课程

POST /api/teacher/course/create

> Body 请求参数

```yaml
file: ""

```

#### 请求参数

| 名称                | 位置  | 类型           | 必选 | 中文名 | 说明                              |
| ------------------- | ----- | -------------- | ---- | ------ | --------------------------------- |
| buyCount            | query | integer(int32) | 否   |        | 销售数量                          |
| cover               | query | string         | 否   |        | 课程封面图片路径                  |
| description         | query | string         | 否   |        | 课程描述                          |
| enable              | query | boolean        | 否   |        | 上架下架，0下架 1上架             |
| id                  | query | integer(int32) | 否   |        | 课程ID                            |
| lessonNum           | query | integer(int32) | 否   |        | 总课时                            |
| price               | query | number(double) | 否   |        | 课程销售价格，设置为0则可免费观看 |
| remarks             | query | string         | 否   |        | 备注                              |
| sort                | query | integer(int32) | 否   |        | 排序                              |
| status              | query | string         | 否   |        | 课程状态，草稿 审核 发表          |
| subjectId           | query | integer(int32) | 否   |        | 课程专业ID                        |
| subjectParent.id    | query | integer(int32) | 否   |        | none                              |
| subjectParent.title | query | string         | 否   |        | 标题                              |
| teacherId           | query | integer(int32) | 否   |        | 课程讲师ID                        |
| teacherName         | query | string         | 否   |        | none                              |
| title               | query | string         | 否   |        | 课程标题                          |
| viewCount           | query | integer(int32) | 否   |        | 浏览数量                          |
| body                | body  | object         | 否   |        | none                              |
| » file              | body  | string(binary) | 否   |        | file                              |

##### 枚举值

| 属性   | 值              |
| ------ | --------------- |
| status | DRAFT           |
| status | PUBLISH         |
| status | TURN_DOWN       |
| status | AUDITING        |
| status | FIRST_AUDITING  |
| status | SECOND_AUDITING |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 下架课程

POST /api/teacher/course/delete/{id}

#### 请求参数

| 名称 | 位置 | 类型           | 必选 | 中文名 | 说明 |
| ---- | ---- | -------------- | ---- | ------ | ---- |
| id   | path | integer(int32) | 是   |        | id   |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### GET 获取该讲师在草稿箱的课程

GET /api/teacher/course/draft

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### GET 获取课程详细的信息

GET /api/teacher/course/info/{id}

#### 请求参数

| 名称 | 位置 | 类型           | 必选 | 中文名 | 说明 |
| ---- | ---- | -------------- | ---- | ------ | ---- |
| id   | path | integer(int32) | 是   |        | id   |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 搜索课程

POST /api/teacher/course/list

> Body 请求参数

```json
{
  "current": 0,
  "enable": true,
  "free": true,
  "pageSize": 0,
  "status": "DRAFT",
  "subjectId": 0,
  "teacherId": 0,
  "title": "string"
}
```

#### 请求参数

| 名称 | 位置 | 类型 | 必选 | 中文名 | 说明 |
| ---- | ---- | ---- | ---- | ------ | ---- |
| body | body | any  | 否   |        | none |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### GET 列出讲师所有的课程id与标题

GET /api/teacher/course/list/all

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 提交审核课程

POST /api/teacher/course/submit/{id}

#### 请求参数

| 名称 | 位置 | 类型           | 必选 | 中文名 | 说明 |
| ---- | ---- | -------------- | ---- | ------ | ---- |
| id   | path | integer(int32) | 是   |        | id   |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 修改课程信息

POST /api/teacher/course/update

> Body 请求参数

```yaml
file: ""

```

#### 请求参数

| 名称                | 位置  | 类型           | 必选 | 中文名 | 说明                              |
| ------------------- | ----- | -------------- | ---- | ------ | --------------------------------- |
| buyCount            | query | integer(int32) | 否   |        | 销售数量                          |
| cover               | query | string         | 否   |        | 课程封面图片路径                  |
| description         | query | string         | 否   |        | 课程描述                          |
| enable              | query | boolean        | 否   |        | 上架下架，0下架 1上架             |
| id                  | query | integer(int32) | 否   |        | 课程ID                            |
| lessonNum           | query | integer(int32) | 否   |        | 总课时                            |
| price               | query | number(double) | 否   |        | 课程销售价格，设置为0则可免费观看 |
| remarks             | query | string         | 否   |        | 备注                              |
| sort                | query | integer(int32) | 否   |        | 排序                              |
| status              | query | string         | 否   |        | 课程状态，草稿 审核 发表          |
| subjectId           | query | integer(int32) | 否   |        | 课程专业ID                        |
| subjectParent.id    | query | integer(int32) | 否   |        | none                              |
| subjectParent.title | query | string         | 否   |        | 标题                              |
| teacherId           | query | integer(int32) | 否   |        | 课程讲师ID                        |
| teacherName         | query | string         | 否   |        | none                              |
| title               | query | string         | 否   |        | 课程标题                          |
| viewCount           | query | integer(int32) | 否   |        | 浏览数量                          |
| body                | body  | object         | 否   |        | none                              |
| » file              | body  | string(binary) | 否   |        | file                              |

##### 枚举值

| 属性   | 值              |
| ------ | --------------- |
| status | DRAFT           |
| status | PUBLISH         |
| status | TURN_DOWN       |
| status | AUDITING        |
| status | FIRST_AUDITING  |
| status | SECOND_AUDITING |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 修改课程章节视频信息

POST /api/teacher/course/update/{id}

#### 请求参数

| 名称 | 位置 | 类型           | 必选 | 中文名 | 说明 |
| ---- | ---- | -------------- | ---- | ------ | ---- |
| id   | path | integer(int32) | 是   |        | id   |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 上传图片

POST /api/teacher/course/upload/pic

> Body 请求参数

```yaml
file: ""

```

#### 请求参数

| 名称   | 位置 | 类型           | 必选 | 中文名 | 说明 |
| ------ | ---- | -------------- | ---- | ------ | ---- |
| body   | body | object         | 否   |        | none |
| » file | body | string(binary) | 是   |        | file |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构

## 讲师端消息接口



### GET 检查是否有未读消息

GET /api/teacher/message/check

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### DELETE 清空所有消息

DELETE /api/teacher/message/clear

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 204    | [No Content](https://tools.ietf.org/html/rfc7231#section-6.3.5) | No Content   | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |

#### 返回数据结构



### DELETE 删除单条消息

DELETE /api/teacher/message/delete/{id}

#### 请求参数

| 名称 | 位置 | 类型           | 必选 | 中文名 | 说明 |
| ---- | ---- | -------------- | ---- | ------ | ---- |
| id   | path | integer(int32) | 是   |        | id   |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 204    | [No Content](https://tools.ietf.org/html/rfc7231#section-6.3.5) | No Content   | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |

#### 返回数据结构



### POST 获取所有消息

POST /api/teacher/message/list

> Body 请求参数

```json
{
  "current": 0,
  "pageSize": 0
}
```

#### 请求参数

| 名称 | 位置 | 类型 | 必选 | 中文名 | 说明 |
| ---- | ---- | ---- | ---- | ------ | ---- |
| body | body | any  | 否   |        | none |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### GET 标记消息为已读

GET /api/teacher/message/read

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构

## 讲师端数据统计接口



### GET 获取讲师数据统计

GET /api/teacher/stat/get/common

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构

## 讲师端讲师接口



### GET 获取登录用户信息

GET /api/teacher/user/info

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 登录

POST /api/teacher/user/login

> Body 请求参数

```json
{
  "password": "string",
  "username": "string"
}
```

#### 请求参数

| 名称 | 位置 | 类型                            | 必选 | 中文名     | 说明 |
| ---- | ---- | ------------------------------- | ---- | ---------- | ---- |
| body | body | [LoginParam](#schemaloginparam) | 否   | LoginParam | none |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 登出

POST /api/teacher/user/logout

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 修改密码

POST /api/teacher/user/update/password

> Body 请求参数

```json
{
  "confirmNewPassword": "string",
  "newPassword": "string",
  "oldPassword": "string"
}
```

#### 请求参数

| 名称 | 位置 | 类型 | 必选 | 中文名 | 说明 |
| ---- | ---- | ---- | ---- | ------ | ---- |
| body | body | any  | 否   |        | none |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构

## 讲师端视频接口（二次修改）



### POST 上传视频

POST /api/teacher/video/tmp/create

> Body 请求参数

```yaml
file: ""

```

#### 请求参数

| 名称   | 位置 | 类型           | 必选 | 中文名 | 说明 |
| ------ | ---- | -------------- | ---- | ------ | ---- |
| body   | body | object         | 否   |        | none |
| » file | body | string(binary) | 是   |        | file |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 删除视频

POST /api/teacher/video/tmp/delete/{id}

#### 请求参数

| 名称 | 位置 | 类型           | 必选 | 中文名 | 说明 |
| ---- | ---- | -------------- | ---- | ------ | ---- |
| id   | path | integer(int64) | 是   |        | id   |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### GET 获取章节视频

GET /api/teacher/video/tmp/list/{chapterId}

#### 请求参数

| 名称      | 位置 | 类型           | 必选 | 中文名 | 说明      |
| --------- | ---- | -------------- | ---- | ------ | --------- |
| chapterId | path | integer(int64) | 是   |        | chapterId |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 更新视频信息

POST /api/teacher/video/tmp/update

> Body 请求参数

```json
{
  "chapterId": "string",
  "courseId": 0,
  "duration": "string",
  "free": true,
  "id": "string",
  "oid": 0,
  "playCount": 0,
  "size": 0,
  "sort": 0,
  "title": "string",
  "videoId": "string"
}
```

#### 请求参数

| 名称 | 位置 | 类型                                  | 必选 | 中文名        | 说明 |
| ---- | ---- | ------------------------------------- | ---- | ------------- | ---- |
| body | body | [EduVideoTmpVO](#schemaeduvideotmpvo) | 否   | EduVideoTmpVO | none |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构

## 讲师端章节接口



### POST 创建章节

POST /api/teacher/chapter/create

> Body 请求参数

```json
{
  "courseId": 0,
  "id": 0,
  "sort": 0,
  "title": "string"
}
```

#### 请求参数

| 名称 | 位置 | 类型                                | 必选 | 中文名       | 说明 |
| ---- | ---- | ----------------------------------- | ---- | ------------ | ---- |
| body | body | [EduChapterVO](#schemaeduchaptervo) | 否   | EduChapterVO | none |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 删除章节

POST /api/teacher/chapter/delete/{id}

#### 请求参数

| 名称 | 位置 | 类型           | 必选 | 中文名 | 说明 |
| ---- | ---- | -------------- | ---- | ------ | ---- |
| id   | path | integer(int32) | 是   |        | id   |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### GET 获取章节

GET /api/teacher/chapter/list/{courseId}

#### 请求参数

| 名称     | 位置 | 类型           | 必选 | 中文名 | 说明     |
| -------- | ---- | -------------- | ---- | ------ | -------- |
| courseId | path | integer(int32) | 是   |        | courseId |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 更新章节信息

POST /api/teacher/chapter/update

> Body 请求参数

```json
{
  "courseId": 0,
  "id": 0,
  "sort": 0,
  "title": "string"
}
```

#### 请求参数

| 名称 | 位置 | 类型                                | 必选 | 中文名       | 说明 |
| ---- | ---- | ----------------------------------- | ---- | ------------ | ---- |
| body | body | [EduChapterVO](#schemaeduchaptervo) | 否   | EduChapterVO | none |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构

## 讲师端视频接口



### GET 获取所有分类

GET /api/teacher/subject/get

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 上传视频

POST /api/teacher/video/create

> Body 请求参数

```yaml
file: ""

```

#### 请求参数

| 名称   | 位置 | 类型           | 必选 | 中文名 | 说明 |
| ------ | ---- | -------------- | ---- | ------ | ---- |
| body   | body | object         | 否   |        | none |
| » file | body | string(binary) | 是   |        | file |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 删除视频

POST /api/teacher/video/delete/{id}

#### 请求参数

| 名称 | 位置 | 类型           | 必选 | 中文名 | 说明 |
| ---- | ---- | -------------- | ---- | ------ | ---- |
| id   | path | integer(int32) | 是   |        | id   |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### GET 获取章节视频

GET /api/teacher/video/list/{chapterId}

#### 请求参数

| 名称      | 位置 | 类型           | 必选 | 中文名 | 说明      |
| --------- | ---- | -------------- | ---- | ------ | --------- |
| chapterId | path | integer(int32) | 是   |        | chapterId |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构



### POST 更新视频信息

POST /api/teacher/video/update

> Body 请求参数

```json
{
  "chapterId": 0,
  "courseId": 0,
  "duration": "string",
  "free": true,
  "id": 0,
  "playCount": 0,
  "size": 0,
  "sort": 0,
  "title": "string",
  "videoId": "string"
}
```

#### 请求参数

| 名称 | 位置 | 类型                            | 必选 | 中文名     | 说明 |
| ---- | ---- | ------------------------------- | ---- | ---------- | ---- |
| body | body | [EduVideoVO](#schemaeduvideovo) | 否   | EduVideoVO | none |

> 返回示例

> 200 Response

#### 返回结果

| 状态码 | 状态码含义                                                   | 说明         | 数据模型 |
| ------ | ------------------------------------------------------------ | ------------ | -------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)      | OK           | Inline   |
| 201    | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2) | Created      | Inline   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1) | Unauthorized | Inline   |
| 403    | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | Forbidden    | Inline   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4) | Not Found    | Inline   |

#### 返回数据结构

## 数据模型

<h2 id="tocS_AclUserDetail">AclUserDetail</h2>






```json
{
  "avatar": "string",
  "enable": true,
  "id": 0,
  "mark": "string",
  "nickname": "string",
  "password": "string",
  "roleId": 0,
  "sign": "string",
  "token": "string",
  "username": "string"
}

```

AclUserDetail

#### 属性

| 名称     | 类型           | 必选  | 约束 | 中文名 | 说明 |
| -------- | -------------- | ----- | ---- | ------ | ---- |
| avatar   | string         | false | none |        | none |
| enable   | boolean        | false | none |        | none |
| id       | integer(int32) | false | none |        | none |
| mark     | string         | false | none |        | none |
| nickname | string         | false | none |        | none |
| password | string         | false | none |        | none |
| roleId   | integer(int32) | false | none |        | none |
| sign     | string         | false | none |        | none |
| token    | string         | false | none |        | none |
| username | string         | false | none |        | none |

<h2 id="tocS_AclUserVO">AclUserVO</h2>






```json
{
  "avatar": "string",
  "enable": true,
  "id": 0,
  "mark": "string",
  "nickname": "string",
  "roleId": 0,
  "sign": "string",
  "token": "string",
  "username": "string"
}

```

AclUserVO

#### 属性

| 名称     | 类型           | 必选  | 约束 | 中文名 | 说明 |
| -------- | -------------- | ----- | ---- | ------ | ---- |
| avatar   | string         | false | none |        | none |
| enable   | boolean        | false | none |        | none |
| id       | integer(int32) | false | none |        | none |
| mark     | string         | false | none |        | none |
| nickname | string         | false | none |        | none |
| roleId   | integer(int32) | false | none |        | none |
| sign     | string         | false | none |        | none |
| token    | string         | false | none |        | none |
| username | string         | false | none |        | none |

<h2 id="tocS_EduChapterTmpVO">EduChapterTmpVO</h2>






```json
{
  "courseId": 0,
  "id": "string",
  "oid": 0,
  "sort": 0,
  "title": "string"
}

```

EduChapterTmpVO

#### 属性

| 名称     | 类型           | 必选  | 约束 | 中文名 | 说明     |
| -------- | -------------- | ----- | ---- | ------ | -------- |
| courseId | integer(int32) | false | none |        | 课程ID   |
| id       | string         | false | none |        | ID       |
| oid      | integer(int32) | false | none |        | none     |
| sort     | integer(int32) | false | none |        | 排序     |
| title    | string         | false | none |        | 章节名称 |

<h2 id="tocS_EduChapterVO">EduChapterVO</h2>






```json
{
  "courseId": 0,
  "id": 0,
  "sort": 0,
  "title": "string"
}

```

EduChapterVO

#### 属性

| 名称     | 类型           | 必选  | 约束 | 中文名 | 说明     |
| -------- | -------------- | ----- | ---- | ------ | -------- |
| courseId | integer(int32) | false | none |        | 课程ID   |
| id       | integer(int32) | false | none |        | ID       |
| sort     | integer(int32) | false | none |        | 排序     |
| title    | string         | false | none |        | 章节名称 |

<h2 id="tocS_EduCommentVO">EduCommentVO</h2>






```json
{
  "content": "string",
  "courseId": 0,
  "courseName": "string",
  "createTime": "2019-08-24T14:15:22Z",
  "id": 0,
  "mark": 0.1,
  "memberAvatar": "string",
  "memberId": 0,
  "memberName": "string",
  "status": true,
  "teacherId": 0
}

```

EduCommentVO

#### 属性

| 名称         | 类型              | 必选  | 约束 | 中文名 | 说明                   |
| ------------ | ----------------- | ----- | ---- | ------ | ---------------------- |
| content      | string            | false | none |        | 评论内容               |
| courseId     | integer(int32)    | false | none |        | 课程id                 |
| courseName   | string            | false | none |        | none                   |
| createTime   | string(date-time) | false | none |        | 创建时间               |
| id           | integer(int32)    | false | none |        | 评论ID                 |
| mark         | number(double)    | false | none |        | 评分（满分5.00）       |
| memberAvatar | string            | false | none |        | none                   |
| memberId     | integer(int32)    | false | none |        | 会员id                 |
| memberName   | string            | false | none |        | none                   |
| status       | boolean           | false | none |        | 评论状态 0审核中 1通过 |
| teacherId    | integer(int32)    | false | none |        | 讲师id                 |

<h2 id="tocS_EduSubjectDetailVO">EduSubjectDetailVO</h2>






```json
{
  "children": [
    {
      "children": [
        {
          "children": [
            {}
          ],
          "enable": true,
          "id": 0,
          "parentId": 0,
          "sort": 0,
          "title": "string"
        }
      ],
      "enable": true,
      "id": 0,
      "parentId": 0,
      "sort": 0,
      "title": "string"
    }
  ],
  "enable": true,
  "id": 0,
  "parentId": 0,
  "sort": 0,
  "title": "string"
}

```

EduSubjectDetailVO

#### 属性

| 名称     | 类型                                              | 必选  | 约束 | 中文名 | 说明             |
| -------- | ------------------------------------------------- | ----- | ---- | ------ | ---------------- |
| children | [[EduSubjectDetailVO](#schemaedusubjectdetailvo)] | false | none |        | 子分类           |
| enable   | boolean                                           | false | none |        | 是否启用，0否1是 |
| id       | integer(int32)                                    | false | none |        | ID               |
| parentId | integer(int32)                                    | false | none |        | 父ID             |
| sort     | integer(int32)                                    | false | none |        | 排序             |
| title    | string                                            | false | none |        | 标题             |

<h2 id="tocS_EduVideoTmpVO">EduVideoTmpVO</h2>






```json
{
  "chapterId": "string",
  "courseId": 0,
  "duration": "string",
  "free": true,
  "id": "string",
  "oid": 0,
  "playCount": 0,
  "size": 0,
  "sort": 0,
  "title": "string",
  "videoId": "string"
}

```

EduVideoTmpVO

#### 属性

| 名称      | 类型           | 必选  | 约束 | 中文名 | 说明                      |
| --------- | -------------- | ----- | ---- | ------ | ------------------------- |
| chapterId | string         | false | none |        | 章节ID                    |
| courseId  | integer(int32) | false | none |        | 课程id                    |
| duration  | string         | false | none |        | 视频时长（秒）            |
| free      | boolean        | false | none |        | 是否可以试听：0免费 1收费 |
| id        | string         | false | none |        | none                      |
| oid       | integer(int32) | false | none |        | none                      |
| playCount | integer(int32) | false | none |        | 播放次数                  |
| size      | integer(int64) | false | none |        | 视频源文件大小（字节）    |
| sort      | integer(int32) | false | none |        | 排序字段                  |
| title     | string         | false | none |        | 视频名称                  |
| videoId   | string         | false | none |        | 云端视频资源              |

<h2 id="tocS_EduVideoVO">EduVideoVO</h2>






```json
{
  "chapterId": 0,
  "courseId": 0,
  "duration": "string",
  "free": true,
  "id": 0,
  "playCount": 0,
  "size": 0,
  "sort": 0,
  "title": "string",
  "videoId": "string"
}

```

EduVideoVO

#### 属性

| 名称      | 类型           | 必选  | 约束 | 中文名 | 说明                      |
| --------- | -------------- | ----- | ---- | ------ | ------------------------- |
| chapterId | integer(int32) | false | none |        | 章节ID                    |
| courseId  | integer(int32) | false | none |        | 课程id                    |
| duration  | string         | false | none |        | 视频时长（秒）            |
| free      | boolean        | false | none |        | 是否可以试听：0免费 1收费 |
| id        | integer(int32) | false | none |        | none                      |
| playCount | integer(int32) | false | none |        | 播放次数                  |
| size      | integer(int64) | false | none |        | 视频源文件大小（字节）    |
| sort      | integer(int32) | false | none |        | 排序字段                  |
| title     | string         | false | none |        | 视频名称                  |
| videoId   | string         | false | none |        | 云端视频资源              |

<h2 id="tocS_LoginParam">LoginParam</h2>






```json
{
  "password": "string",
  "username": "string"
}

```

LoginParam

#### 属性

| 名称     | 类型   | 必选  | 约束 | 中文名 | 说明 |
| -------- | ------ | ----- | ---- | ------ | ---- |
| password | string | false | none |        | none |
| username | string | false | none |        | none |

<h2 id="tocS_RegisterParam">RegisterParam</h2>






```json
{
  "confirmPassword": "string",
  "password": "string",
  "username": "string"
}

```

RegisterParam

#### 属性

| 名称            | 类型   | 必选  | 约束 | 中文名 | 说明         |
| --------------- | ------ | ----- | ---- | ------ | ------------ |
| confirmPassword | string | false | none |        | 确认的新密码 |
| password        | string | false | none |        | 密码         |
| username        | string | false | none |        | none         |

<h2 id="tocS_TOrderVO">TOrderVO</h2>






```json
{
  "courseId": 0,
  "createTime": "2019-08-24T14:15:22Z",
  "id": 0,
  "memberId": 0,
  "orderNo": "string",
  "payTime": "2019-08-24T14:15:22Z",
  "payType": "NONE",
  "totalFee": 0.1,
  "transactionNum": "string"
}

```

TOrderVO

#### 属性

| 名称           | 类型              | 必选  | 约束 | 中文名 | 说明                                    |
| -------------- | ----------------- | ----- | ---- | ------ | --------------------------------------- |
| courseId       | integer(int32)    | false | none |        | 课程id                                  |
| createTime     | string(date-time) | false | none |        | 创建时间                                |
| id             | integer(int32)    | false | none |        | none                                    |
| memberId       | integer(int32)    | false | none |        | 会员id                                  |
| orderNo        | string            | false | none |        | none                                    |
| payTime        | string(date-time) | false | none |        | 支付完成时间                            |
| payType        | string            | false | none |        | 支付类型（0：未支付 1：微信 2：支付宝） |
| totalFee       | number(double)    | false | none |        | 订单金额（元）                          |
| transactionNum | string            | false | none |        | 交易成功的流水号                        |

##### 枚举值

| 属性    | 值         |
| ------- | ---------- |
| payType | NONE       |
| payType | WECHAT_PAY |
| payType | ALI_PAY    |

<h2 id="tocS_UctrMemberDetailVO">UctrMemberDetailVO</h2>






```json
{
  "age": 0,
  "avatar": "string",
  "createTime": "2019-08-24T14:15:22Z",
  "email": "string",
  "enable": true,
  "id": 0,
  "mobile": "string",
  "nickname": "string",
  "sex": "SECRET",
  "sign": "string",
  "token": "string"
}

```

UctrMemberDetailVO

#### 属性

| 名称       | 类型              | 必选  | 约束 | 中文名 | 说明                   |
| ---------- | ----------------- | ----- | ---- | ------ | ---------------------- |
| age        | integer(int32)    | false | none |        | none                   |
| avatar     | string            | false | none |        | none                   |
| createTime | string(date-time) | false | none |        | none                   |
| email      | string            | false | none |        | 邮箱地址               |
| enable     | boolean           | false | none |        | none                   |
| id         | integer(int32)    | false | none |        | none                   |
| mobile     | string            | false | none |        | 手机号                 |
| nickname   | string            | false | none |        | none                   |
| sex        | string            | false | none |        | 性别 0 保密 1 女，2 男 |
| sign       | string            | false | none |        | none                   |
| token      | string            | false | none |        | none                   |

##### 枚举值

| 属性 | 值     |
| ---- | ------ |
| sex  | SECRET |
| sex  | FEMALE |
| sex  | MALE   |

<h2 id="tocS_后台用户搜索参数">后台用户搜索参数</h2>






```json
{
  "current": 0,
  "enable": true,
  "pageSize": 0,
  "roleId": 0,
  "username": "string"
}

```

后台用户搜索参数

#### 属性

| 名称     | 类型           | 必选  | 约束 | 中文名 | 说明     |
| -------- | -------------- | ----- | ---- | ------ | -------- |
| current  | integer(int64) | false | none |        | 当前页   |
| enable   | boolean        | false | none |        | none     |
| pageSize | integer(int64) | false | none |        | 每页数量 |
| roleId   | integer(int32) | false | none |        | none     |
| username | string         | false | none |        | none     |

<h2 id="tocS_基础分页参数">基础分页参数</h2>






```json
{
  "current": 0,
  "pageSize": 0
}

```

基础分页参数

#### 属性

| 名称     | 类型           | 必选  | 约束 | 中文名 | 说明     |
| -------- | -------------- | ----- | ---- | ------ | -------- |
| current  | integer(int64) | false | none |        | 当前页   |
| pageSize | integer(int64) | false | none |        | 每页数量 |

<h2 id="tocS_学员搜索参数">学员搜索参数</h2>






```json
{
  "beginCreate": "2019-08-24T14:15:22Z",
  "current": 0,
  "enable": true,
  "endCreate": "2019-08-24T14:15:22Z",
  "mobile": "string",
  "nickname": "string",
  "pageSize": 0
}

```

学员搜索参数

#### 属性

| 名称        | 类型              | 必选  | 约束 | 中文名 | 说明           |
| ----------- | ----------------- | ----- | ---- | ------ | -------------- |
| beginCreate | string(date-time) | false | none |        | 大于该注册时间 |
| current     | integer(int64)    | false | none |        | 当前页         |
| enable      | boolean           | false | none |        | none           |
| endCreate   | string(date-time) | false | none |        | 小于该注册时间 |
| mobile      | string            | false | none |        | none           |
| nickname    | string            | false | none |        | none           |
| pageSize    | integer(int64)    | false | none |        | 每页数量       |

<h2 id="tocS_密码更新参数">密码更新参数</h2>






```json
{
  "confirmNewPassword": "string",
  "newPassword": "string",
  "oldPassword": "string"
}

```

密码更新参数

#### 属性

| 名称               | 类型   | 必选  | 约束 | 中文名 | 说明         |
| ------------------ | ------ | ----- | ---- | ------ | ------------ |
| confirmNewPassword | string | false | none |        | 确认的新密码 |
| newPassword        | string | false | none |        | 新密码       |
| oldPassword        | string | false | none |        | 原密码       |

<h2 id="tocS_管理员权限更新密码参数">管理员权限更新密码参数</h2>






```json
{
  "newPassword": "string",
  "userId": 0
}

```

管理员权限更新密码参数

#### 属性

| 名称        | 类型           | 必选  | 约束 | 中文名 | 说明   |
| ----------- | -------------- | ----- | ---- | ------ | ------ |
| newPassword | string         | false | none |        | 新密码 |
| userId      | integer(int32) | false | none |        | 用户id |

<h2 id="tocS_统一响应结果">统一响应结果</h2>






```json
{
  "data": {},
  "message": "string",
  "status": 0,
  "timestamp": "2019-08-24T14:15:22Z"
}

```

统一响应结果

#### 属性

| 名称      | 类型              | 必选  | 约束 | 中文名 | 说明       |
| --------- | ----------------- | ----- | ---- | ------ | ---------- |
| data      | object            | false | none |        | 响应数据   |
| message   | string            | false | none |        | 响应消息   |
| status    | integer(int32)    | false | none |        | 响应状态值 |
| timestamp | string(date-time) | false | none |        | 响应时间   |

<h2 id="tocS_订单搜索参数">订单搜索参数</h2>






```json
{
  "beginCreate": "2019-08-24T14:15:22Z",
  "current": 0,
  "endCreate": "2019-08-24T14:15:22Z",
  "memberId": 0,
  "orderNo": "string",
  "pageSize": 0,
  "payType": "NONE"
}

```

订单搜索参数

#### 属性

| 名称        | 类型              | 必选  | 约束 | 中文名 | 说明               |
| ----------- | ----------------- | ----- | ---- | ------ | ------------------ |
| beginCreate | string(date-time) | false | none |        | 大于该订单创建时间 |
| current     | integer(int64)    | false | none |        | 当前页             |
| endCreate   | string(date-time) | false | none |        | 小于该订单创建时间 |
| memberId    | integer(int32)    | false | none |        | none               |
| orderNo     | string            | false | none |        | none               |
| pageSize    | integer(int64)    | false | none |        | 每页数量           |
| payType     | string            | false | none |        | none               |

##### 枚举值

| 属性    | 值         |
| ------- | ---------- |
| payType | NONE       |
| payType | WECHAT_PAY |
| payType | ALI_PAY    |

<h2 id="tocS_讲师搜索参数">讲师搜索参数</h2>






```json
{
  "current": 0,
  "enable": true,
  "mobile": "string",
  "name": "string",
  "pageSize": 0,
  "status": "PASS"
}

```

讲师搜索参数

#### 属性

| 名称     | 类型           | 必选  | 约束 | 中文名 | 说明     |
| -------- | -------------- | ----- | ---- | ------ | -------- |
| current  | integer(int64) | false | none |        | 当前页   |
| enable   | boolean        | false | none |        | none     |
| mobile   | string         | false | none |        | none     |
| name     | string         | false | none |        | none     |
| pageSize | integer(int64) | false | none |        | 每页数量 |
| status   | string         | false | none |        | none     |

##### 枚举值

| 属性   | 值       |
| ------ | -------- |
| status | PASS     |
| status | AUDITING |
| status | NOT_PASS |

<h2 id="tocS_评论搜索参数">评论搜索参数</h2>






```json
{
  "courseId": 0,
  "current": 0,
  "pageSize": 0,
  "status": true
}

```

评论搜索参数

#### 属性

| 名称     | 类型           | 必选  | 约束 | 中文名 | 说明     |
| -------- | -------------- | ----- | ---- | ------ | -------- |
| courseId | integer(int32) | false | none |        | none     |
| current  | integer(int64) | false | none |        | 当前页   |
| pageSize | integer(int64) | false | none |        | 每页数量 |
| status   | boolean        | false | none |        | none     |

<h2 id="tocS_课程分类搜索参数">课程分类搜索参数</h2>






```json
{
  "current": 0,
  "enable": true,
  "pageSize": 0,
  "parentId": 0,
  "title": "string"
}

```

课程分类搜索参数

#### 属性

| 名称     | 类型           | 必选  | 约束 | 中文名 | 说明     |
| -------- | -------------- | ----- | ---- | ------ | -------- |
| current  | integer(int64) | false | none |        | 当前页   |
| enable   | boolean        | false | none |        | none     |
| pageSize | integer(int64) | false | none |        | 每页数量 |
| parentId | integer(int32) | false | none |        | none     |
| title    | string         | false | none |        | none     |

<h2 id="tocS_课程搜索参数">课程搜索参数</h2>






```json
{
  "current": 0,
  "enable": true,
  "free": true,
  "pageSize": 0,
  "status": "DRAFT",
  "subjectId": 0,
  "teacherId": 0,
  "title": "string"
}

```

课程搜索参数

#### 属性

| 名称      | 类型           | 必选  | 约束 | 中文名 | 说明                              |
| --------- | -------------- | ----- | ---- | ------ | --------------------------------- |
| current   | integer(int64) | false | none |        | 当前页                            |
| enable    | boolean        | false | none |        | 上架下架，0下架 1上架             |
| free      | boolean        | false | none |        | 课程销售价格，设置为0则可免费观看 |
| pageSize  | integer(int64) | false | none |        | 每页数量                          |
| status    | string         | false | none |        | 课程状态，草稿 审核 发表          |
| subjectId | integer(int32) | false | none |        | 课程专业ID                        |
| teacherId | integer(int32) | false | none |        | 课程讲师ID                        |
| title     | string         | false | none |        | 课程标题                          |

##### 枚举值

| 属性   | 值              |
| ------ | --------------- |
| status | DRAFT           |
| status | PUBLISH         |
| status | TURN_DOWN       |
| status | AUDITING        |
| status | FIRST_AUDITING  |
| status | SECOND_AUDITING |

<h2 id="tocS_首页Banner搜索参数">首页Banner搜索参数</h2>






```json
{
  "current": 0,
  "enable": true,
  "pageSize": 0,
  "title": "string"
}

```

首页Banner搜索参数

#### 属性

| 名称     | 类型           | 必选  | 约束 | 中文名 | 说明     |
| -------- | -------------- | ----- | ---- | ------ | -------- |
| current  | integer(int64) | false | none |        | 当前页   |
| enable   | boolean        | false | none |        | none     |
| pageSize | integer(int64) | false | none |        | 每页数量 |
| title    | string         | false | none |        | none     |



## 八、展望

现在已经完成在线课程平台的大体框架，后续准备在现有基础上借助google analytics加入点击流分析工作，为学生、讲师和管理员提供便捷且全面的学习与教学体验。