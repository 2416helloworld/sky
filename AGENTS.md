# AI Agent Instructions for 知光后端项目

## 目标
帮助 AI 迅速理解本仓库后端架构，并生成适合面试讲解的项目说明、模块划分和关键技术点。

## 项目定位
这是 `zhiguang_be` 后端服务，属于知识社区类产品“知光平台”的服务端实现。
核心业务包括：用户认证、关注关系、内容发布与 Feed、计数系统、搜索、AI 问答、对象存储直传、以及异步事件驱动增长系统。

## 主要模块与目录
- `src/main/java/com/tongji/auth`：认证与授权
  - JWT 双令牌认证、RS256 签名、Redis 刷新令牌白名单、验证码校验、登录登出、注册
- `src/main/java/com/tongji/counter`：计数系统
  - 关注、点赞、收藏等计数；Redis 底层、Lua 原子更新、位图幂等、异步写入、可重建计数
- `src/main/java/com/tongji/relation`：用户关系
  - 关注/取关、Outbox 事件写入、Canal 订阅、Kafka 异步更新读模型
- `src/main/java/com/tongji/knowpost`：知识帖（KnowPost）内容与 Feed
  - 文章发布、草稿、AI 生成摘要、动态 Feed、发布审核、缓存失效监听
- `src/main/java/com/tongji/search`：搜索与联想
  - Elasticsearch 索引、搜索分页、completion suggester、BM25+业务权重排序
- `src/main/java/com/tongji/llm`：AI 与 RAG
  - 向量检索、Prompt 构造、DeepSeek/OpenAI 调用、知识问答流程
- `src/main/java/com/tongji/storage`：对象存储
  - OSS 直传预签名、文件上传接口、存储服务配置
- `src/main/java/com/tongji/profile`：用户资料与配置
- `src/main/java/com/tongji/cache`：本地缓存与热点检测
  - Caffeine + Redis 三级缓存、hotkey 探测、缓存延长策略
- `src/main/java/com/tongji/common`：通用异常、工具、Web 统一异常处理
- `src/main/java/com/tongji/config`：全局配置类与基础组件

## 重要技术点
- Java 21 + Spring Boot + MyBatis
- Spring Security + JWT + Redis + RSA 密钥对
- Kafka 事件异步写入与分布式回放
- Canal 订阅 MySQL binlog → Kafka 异步更新读模型
- Elasticsearch 搜索 + completion suggest + function_score 排序融合业务权重
- OSS 对象存储直传 + 预签名 URL
- AI 模块集成 DeepSeek/OpenAI，支持向量检索和 RAG

## 重要配置与运行点
- 核心启动类：`src/main/java/com/tongji/ZhiGuangApplication.java`
- 运行配置：`src/main/resources/application.yml`
- MyBatis mapper XML：`src/main/resources/mapper/*.xml`

## 文档与设计参考
本仓库包含项目设计文档，AI 应优先引用而不是复制：
- `docs/计数系统设计方案.md`
- `docs/用户关系设计方案.md`
- `docs/API接口文档.md`
- `docs/API接口文档_计数.md`
- `docs/API接口文档_用户关系.md`
- `docs/API接口文档_knowpost.md`

## 面试讲解建议
当用户要求“讲解项目”或“准备面试回答”时，生成回答应包含：
1. 项目是什么：知识社区后端、支持内容发布、用户关系、搜索、AI 问答。
2. 核心架构：Spring Boot 微服务风格、模块化目录、Redis+Kafka+Canal+ES 组合。
3. 核心亮点：JWT 双令牌、Redis 原子计数、Outbox + Canal 异步一致性、RAG AI 问答。
4. 关键流程示例：注册登录流程、关注关系写入流程、发布内容后缓存与搜索索引更新流程、AI 问答流程。
5. 可用的文档与代码位置：`README.md`、`application.yml`、`docs/*.md`、`src/main/java/com/tongji/...`。

## 生成内容时的行为准则
- 不要泄露项目中的真实秘密或 API Keys。
- 优先说明架构设计和业务目标，而不是逐行解释代码。
- 若用户要求代码实现细节，可引用对应模块与关键类，而不是复制整个实现。
- 若用户提出“如何运行”类问题，可推荐 `mvn clean package` 和使用 `application.yml` 本地配置。

## 推荐回答格式
- 简洁、分段说明
- 用“项目背景”、“模块划分”、“核心技术”、“关键流程”、“面试重点”五部分组织内容
- 直接指出“该仓库后端不含前端，前端另见 zhiguang_fe”

## 额外说明
本仓库主要是后端实现，前端代码不包含在此仓库内。
如果需要前端结构解析，应建议用户查看对应仓库。