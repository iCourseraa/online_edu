

## 环境安装

### 1.java

```bash
sudo apt-get install -y default-jdk
```
### 2.Redis

```bash
apt-get install redis -y
sudo systemctl start redis
```

### 3.MySQL(debain12)

修改/etc/apt/sources.list.d/mysql.list

```
deb [trusted=yes] http://repo.mysql.com/apt/debian/ bullseye mysql-apt-config
deb [trusted=yes] http://repo.mysql.com/apt/debian/ bullseye mysql-8.0
deb [trusted=yes] http://repo.mysql.com/apt/debian/ bullseye mysql-tools
deb-src [trusted=yes] http://repo.mysql.com/apt/debian/ bullseye mysql-8.0
```

或者

参考[Debian12安装MySQL8、创建新用户、授权实践及问题解决方案 - lym003 - 博客园](https://www.cnblogs.com/lym003/p/17757210.html)

```
sudo apt update
wget https://dev.mysql.com/get/mysql-apt-config_0.8.25-1_all.deb
sudo dpkg -i mysql-apt-config_0.8.25-1_all.deb

sudo apt search  mecab-ipadic-utf8
sudo apt install  mecab-ipadic-utf8

wget https://mirrors.tuna.tsinghua.edu.cn/debian/pool/main/o/openssl/libssl1.1_1.1.1n-0+deb10u3_amd64.deb
sudo dpkg -i libssl1.1_1.1.1n-0+deb10u3_amd64.deb

sudo apt install mysql-server
```



登录 MySQL

```bash
mysql -u root -p
```

创建数据库

```sql
CREATE DATABASE online_edu CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

切换到 `online_edu` 数据库

```sql
USE online_edu;
exit
```

进入包含 `schema.sql` 的目录

```bash
cd /root/online_edu/online-edu-master-back
```

执行 SQL 文件

```sql
mysql -u root -p online_edu < schema.sql
```

## mvn

在当前路径中上传jave-1.0.2.jar和aliyun-java-vod-upload-1.4.15.jar

[jave下载链接🔗](https://www.sauronsoftware.it/projects/jave/download.php)

[aliyun下载链接🔗](https://help.aliyun.com/zh/vod/developer-reference/sdk-overview-and-download?spm=a2c4g.94311.0.0.3a3624cbBAPROm#section-dd8-5u2-5b9)

运行

```bash
mvn install:install-file -Dfile=jave-1.0.2.jar -DgroupId=it.sauronsoftware -DartifactId=jave -Dversion=1.0.2 -Dpackaging=jar

mvn install:install-file -Dfile=aliyun-java-vod-upload-1.4.15.jar -DgroupId=com.aliyun -DartifactId=aliyun-java-vod-upload -Dversion=1.4.15 -Dpackaging=jar
```

清理并构建项目

```bash
mvn clean install
```



## 守护进程启动

### 1.编写守护进程文件

```
sudo nano /etc/systemd/system/online-edu.service
```

> 文件内容
>
> ```bash
> [Unit]
> Description=Online Edu Service
> After=network.target
> 
> [Service]
> # 用户和组运行服务
> User=root
> Group=root
> 
> # Java命令执行路径
> WorkingDirectory=/path/to/online-edu-master-back/target ## 改为自己的路径
> ExecStart=/usr/bin/java -jar /path/to/online-edu-master-back/target/online-edu-0.0.1-SNAPSHOT.jar                                      ## 改为自己的路径
> 
> # 环境变量（如果有需要可以添加）
> Environment=JAVA_OPTS="-Xmx512m -Xms256m"
> 
> # 重启策略
> Restart=always
> RestartSec=10
> 
> [Install]
> WantedBy=multi-user.target
> 
> ```

### 2.重新加载守护进程

```bash
sudo systemctl daemon-reload
```

### 3.启动守护进程

```bash
sudo systemctl start online-edu.service
```



