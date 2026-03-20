# 🧹 ClearItems - Fabric 1.21.x

![ClearItems Icon](src/main/resources/icon.png)

**ClearItems** 是一款专为 Fabric 1.21.x 开发的轻量级、可视化服务器掉落物清理模组。它结合了直观的倒计时系统与灵活的白名单管理，旨在为服务器提供高效优化的同时，确保玩家物资的安全。

## ✨ 核心特性

* **📊 全程可视化**：屏幕上方常驻蓝色 **BossBar** 进度条，实时显示下一次清理的倒计时，让玩家有充裕时间回收重要物品。
* **🛡️ 智能动态白名单**：支持在游戏内实时添加/移除受保护物品。被加入白名单的物品（如钻石、附魔书等）将永远不会被自动清理。
* **📈 精准清理战报**：清理完成后，系统会精确统计并通报掉落物的**具体总个数**（例如：一堆64个石头计为64个，而非1个实体），让优化效果一目了然。
* **⚙️ 即时生效配置**：所有指令修改（时间间隔、白名单增删）均即时生效并自动持久化到 `config/clearitems.json`，无需重启服务器。
* **🚀 极简性能开销**：基于原生 Fabric API 构建，代码逻辑简洁，不占额外内存或 CPU 资源。

## 🎮 指令指南

模组主指令为 `/clearitems`，支持以下子指令：

| 指令 | 说明 | 使用示例 |
| :--- | :--- | :--- |
| `set <秒>` | 修改自动清理的时间间隔（1-3600秒） | `/clearitems set 600` |
| `add <物品ID>` | 将指定物品加入保护名单（支持 Tab 补全） | `/clearitems add minecraft:diamond` |
| `remove <物品ID>` | 将物品从保护名单中移除 | `/clearitems remove minecraft:stone` |
| `clear` | 立即执行一次手动清理并发布统计战报 | `/clearitems clear` |
| `start` | 重置计时并开启自动清理 | `/clearitems start` |
| `stop` | 停止自动清理逻辑并隐藏 BossBar | `/clearitems stop` |

## 🛠️ 安装要求

1.  **游戏版本**: Minecraft `1.21.x`
2.  **加载器**: [Fabric Loader](https://fabricmc.net/)
3.  **前置插件**: [Fabric API](https://modrinth.com/mod/fabric-api)

> **注意**：该mod已移除 OP 权限限制。如果你的服务器属于公网开放服务器且担心恶意操作，请在配置文件中手动管理或使用权限插件。

## ⚖️ 开源协议

本项目采用 MIT License 开源。您可以自由地分发、修改和在您的服务器中使用。

## 🚀 v1.1.1 核心特性

* **🟢 轻量级 ActionBar 提醒**：倒计时由屏幕顶部的进度条转为快捷栏上方的**彩色文字**显示。
    * **优势**：不占用 BossBar 资源，完美兼容其他修改 UI、Boss 血条或任务提示的模组，不遮挡战斗视线。
    * **智能变色**：根据剩余时间自动切换（绿色 > 黄色 > 红色加粗），视觉反馈直观。
* **🛠️ 极致稳定性修复**：彻底修复了在重度混淆环境下因权限判定导致的 `NoSuchMethodError` 问题，确保在重型整合包中秒速启动。
* **🛡️ 智能动态白名单**：支持在游戏内实时 `add`（添加）或 `remove`（移除）受保护物品。
* **📋 白名单查询**：**[新]** 使用 `/clearitems list` 指令一键查看当前所有受保护物品。
* **📈 精准清理战报**：精确统计并通报掉落物的**具体总个数**（如：一组 64 个物品计为 64 个），优化效果一目了然。

## 🗺️ 开发计划 (Roadmap)
* **v1.1.1 (Current)**: 专注于 ActionBar 显示与极致稳定性。
* **v1.1.2 (Upcoming)**: 计划回归 **BossBar (进度条)** 显示模式，并提供显示切换功能，满足更多视觉偏好。

## 📝 配置文件示例

文件位于 `.minecraft/config/clearitems.json`，格式如下：

```json
{
  "interval": 300,
  "whiteList": [
    "minecraft:diamond",
    "minecraft:netherite_ingot",
    "minecraft:beacon"
  ]
}
