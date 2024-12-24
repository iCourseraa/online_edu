## node 安装

### 安装nvm

```
curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.5/install.sh | bash
source ~/.bashrc
```

### 切换node版本

```
nvm install 16.20.2
nvm use 16.20.2
```

## 安装环境

### 清理并重新安装依赖

```
rm -rf node_modules package-lock.json
npm cache clean --force
npm install
```

## 构建

### admin

```
npm run build:prod
```

### techer

```
npm run build:prod
```

### app

```
npm run build
```

### 移动构建的文件

```
cp /path/to/OnlineEdu-Admin-master-front/dist/* /var/www/online_edu_admin/

cp /path/to/OnlineEdu-App-master-front/dist/* /var/www/online_edu_app/

cp /path/to/online-edu-teacher-master-front/dist/* /var/www/online_edu_teacher/
```



## nginx

### 安装

```
apt install nginx
cd /etc/nginx
```

### 修改配置文件nginx.conf

```
user www-data;
worker_processes auto;
pid /run/nginx.pid;
error_log /var/log/nginx/error.log;

events {
    worker_connections 768;
}

http {
    # 基本配置
    include /etc/nginx/mime.types;
    default_type application/octet-stream;

    # 全局上传文件大小限制
    client_max_body_size 1g;                      # 单个请求最大大小设置为 1GB
    client_body_buffer_size 128k;                # 上传请求的缓冲区大小
    client_body_temp_path /var/cache/nginx/client_temp; # 上传时临时存储路径

    # 添加站点配置
    server {
        listen 80 default_server;
        server_name _;

        # 默认 root 空目录，防止路径泄露
        root /var/www/empty;

        # 前端路径
        location /admin {
            alias /var/www/online_edu_admin;
            index index.html;
            try_files $uri $uri/ /index.html;
        }

        location /teacher {
            alias /var/www/online_edu_teacher;
            index index.html;
            try_files $uri $uri/ /index.html;
        }

        location /app {
            alias /var/www/online_edu_app;
            index index.html;
            try_files $uri $uri/ /index.html;
        }

        # 后端接口代理
        location /api {
            proxy_pass http://127.0.0.1:9096; # 将 /api 路径代理到后端服务
            proxy_set_header Host $host;
            proxy_set_header X-Real-IP $remote_addr;
            proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
            proxy_set_header X-Forwarded-Proto $scheme;

            # 缓冲大文件到后端，确保稳定传输
            proxy_request_buffering on;
            proxy_http_version 1.1;  # 确保支持 HTTP/1.1
        }

        # 上传接口配置
        location /api/upload {
            proxy_pass http://127.0.0.1:9096; # 上传接口转发到后端服务
            proxy_set_header Host $host;
            proxy_set_header X-Real-IP $remote_addr;
            proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
            proxy_set_header X-Forwarded-Proto $scheme;

            # 超时设置
            client_body_timeout 300s;
            send_timeout 300s;
            proxy_connect_timeout 300s;
            proxy_read_timeout 300s;
            proxy_send_timeout 300s;
        }

        # 处理未匹配的请求
        location / {
            return 404; # 默认返回 404
        }

        # 禁止访问的路径
        location ~* /(vendor|workspace|panel|public|containers) {
            return 403;
        }

        # 日志 (optional)
        error_log /var/log/nginx/custom_error.log;
        access_log /var/log/nginx/custom_access.log;
    }
}
```

### 检查问题

```
sudo nginx -t
```

### 重新加载配置

```
systemctl reload nginx
```

### 启动服务

```
systemctl restart nginx
```

