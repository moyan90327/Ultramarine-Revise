# Ultramarine Revise for Minecraft 1.21.11

这是基于 [Ultramarine-Rekindled](https://github.com/Flechazo098/Ultramarine-Rekindled) `fabric-26.1` 分支移植到 Minecraft 1.21.11 的 Fabric 版本。

项目保留了 26.1 分支的方块、物品、模型、贴图、配方、界面和集成功能，并将 Minecraft/Fabric 26.1 API 适配到 Minecraft 1.21.11。

## 环境

- Minecraft 1.21.11
- Fabric Loader 0.18.4 或更高版本
- Fabric API 0.141.6+1.21.11
- Java 21
- Gradle 9.7.1（由 Gradle Wrapper 管理）

可选兼容：

- Roughly Enough Items 21.11.816
- Just Enough Items 27.38.0.97
- Jade 21.1.6

## 构建

Windows：

```powershell
.\gradlew.bat clean build
```

Linux/macOS：

```bash
./gradlew clean build
```

构建产物位于 `build/libs/`。其中不带 `-sources` 后缀的 JAR 是游戏模组文件。

## 安装

1. 安装 Minecraft 1.21.11、Fabric Loader 和 Fabric API。
2. 将构建出的模组 JAR 放入游戏实例的 `mods` 目录。
3. 删除同一实例中其他版本的 Ultramarine/群青 JAR，避免重复加载。

## 1.21.11 适配说明

本移植包含以下兼容工作：

- Java 25 语法回移到 Java 21。
- 自定义配方结果、Codec 和网络序列化适配。
- 26.1 GUI 提取式渲染适配为 1.21.11 `GuiGraphics` 渲染。
- 创造模式标签、燃料、POI、物品传输和服务器 Tick API 适配。
- 木工配方 S2C 同步适配。
- Cook 职业、原版职业和流浪商人交易适配为 1.21.11 Fabric 交易事件。
- 旅行商人存档和世界生成数据提供器适配。
- Fabric 模组元数据更新到 Minecraft 1.21.11。

## 上游与许可

本项目是社区移植版本，不代表原作者或上游维护者的官方发布。

- 原始项目：[LocusAzzurro/Ultramarine](https://github.com/LocusAzzurro/Ultramarine)
- Fabric 移植基线：[Flechazo098/Ultramarine-Rekindled](https://github.com/Flechazo098/Ultramarine-Rekindled)
- 基线分支：`fabric-26.1`
- 基线提交：`992be79f7c3ac53e0f0088af2a00bb73caae5cea`

感谢 **Flechazo098** 提供的技术支持，包括透明方块渲染层注册方案，并感谢其允许本项目进行开源二次修改与发布。

源代码及二进制遵循 BSD-3-Clause。贴图、模型、音频、文本等资源遵循 CC BY-NC 4.0，禁止商业使用。详见 [LICENSE](LICENSE) 和 [ATTRIBUTION.md](ATTRIBUTION.md)。
