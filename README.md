### 目录概要
#####一、命名描述 
1. firechat-base- 为基础类，以jar形式被依赖
2. firechat-cloud- 为微服务项目module
#####二、目录结构
- firechat-base-common 基础公共层（utils、entity(po、vo、dto)、enums）
- firechat-base-dao 基础数据层(操作数据库统一写在这里)
- firechat-base-service 基础业务层(开发时需分析业务是否有公用性)
- firechat-cloud-app Im的接口服务（第一版实现环信，业务代码在这边实现）
- firechat-cloud-admin 后台管理服务
- firechat-cloud-zuul zuul网关
### Nacos 注册中心与配置中心
- 地址： http://47.116.79.70:8852/nacos/
- 账号/密码： nacos / nacos
- 官方文档： https://nacos.io/zh-cn/docs/what-is-nacos.html
- 安装目录（47.116.79.70）：/home/nacos/