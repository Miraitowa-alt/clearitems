<div align="center">
  <img src="https://raw.githubusercontent.com/Miraitowa-alt/clearitems/main/src/main/resources/icon.png" width="128" height="128" alt="ClearItems Icon">

  # ClearItems - 自动化实体清理 / Automated Entity Cleanup

  [![Minecraft](https://img.shields.io/badge/Minecraft-1.21.x-blue.svg?style=flat-square&logo=minecraft)](https://modrinth.com/mod/clearitems)
  [![Platform](https://img.shields.io/badge/Platform-Fabric-lightgrey.svg?style=flat-square)](https://fabricmc.net/)
  [![License](https://img.shields.io/badge/License-MIT-green.svg?style=flat-square)](LICENSE)
  [![Modrinth](https://img.shields.io/badge/Modrinth-Download-00AF5C.svg?style=flat-square)](https://modrinth.com/mod/clearitems)

  **轻量、可视化的生产级 Fabric 清理方案 / Lightweight & Visualized Cleanup for Fabric.**
</div>

---

## 📖 简介 / Introduction

**ClearItems** 是一款专为 Fabric 1.21.x 开发的实体清理模组。它结合了直观的倒计时系统与灵活的白名单管理，旨在为服务器提供高效优化的同时，确保玩家物资安全。

**ClearItems** is a cleanup mod for Fabric 1.21.x. It combines an intuitive countdown system with flexible whitelist management, ensuring server optimization without compromising player resources.

---

## ✨ 核心特性 / Key Features

* **📊 全程可视化 / Full Visualization**
  使用快捷栏上方的 **ActionBar** 实时显示彩色倒计时。根据剩余时间自动切换（绿色 > 黄色 > 红色加粗），不遮挡战斗视线，完美兼容重型整合包。
  *Real-time **ActionBar** countdown with dynamic colors (Green > Yellow > Bold Red). Avoids HUD clutter in massive modpacks.*

* **🛡️ 智能动态白名单 / Smart Dynamic Whitelist**
  支持在游戏内通过 `/clearitems add/remove` 实时管理受保护物品（如钻石、附魔书）。
  *Manage protected items (e.g., diamonds, enchanted books) in-game via commands.*

* **📈 精准清理战报 / Precise Reporting**
  精确统计掉落物的**具体总个数**（如：一组 64 个物品计为 64 个），优化效果一目了然。
  *Accurately counts total item stacks (e.g., 64 items counted as 64, not 1 entity).*

* **🚀 极简性能开销 / Minimal Overhead**
  基于原生 Fabric API 构建，针对重度混淆环境深度优化，确保秒速启动与极端稳定性。
  *Built on native Fabric API; optimized for heavily obfuscated environments and stability.*

---

## 🎮 指令指南 / Commands

| 指令 (Command) | 说明 (Description) | 使用示例 (Example) |
| :--- | :--- | :--- |
| `set <seconds>` | 修改自动清理的时间间隔 (1-3600s) / Set interval | `/clearitems set 600` |
| `add <item_id>` | 将物品加入白名单 / Add item to whitelist | `/clearitems add minecraft:diamond` |
| `remove <id>` | 将物品从白名单移除 / Remove from whitelist | `/clearitems remove minecraft:stone` |
| `list` | **[New]** 查看当前白名单 / View whitelist | `/clearitems list` |
| `clear` | 立即执行清理并发布战报 / Immediate cleanup | `/clearitems clear` |
| `start / stop` | 开启或停止自动清理逻辑 / Toggle auto-cleanup | `/clearitems stop` |

---

## 🛠️ 安装要求 / Requirements

1. **Minecraft**: `1.21.x`
2. **Fabric Loader** & **[Fabric API](https://modrinth.com/mod/fabric-api)**
3. **注意 / Notice**: 本模组已移除硬编码的 OP 权限限制，建议公网服务器配合 **LuckPerms** 使用权限节点（如 `clearitems.admin`）。
   *Mandatory OP checks removed; use **LuckPerms** for node-based control on public servers.*

---

## 🗺️ 开发计划 / Roadmap

- [x] **v1.1.1 (Current)**: 专注于 ActionBar 显示、稳定性修复与 `/clearitems list` 功能。
  *Focused on ActionBar display, stability fixes, and whitelist query.*
- [ ] **v1.2.0 (Developing)**: 
  - [ ] 引入 `config.json` 配置文件系统与热加载。 / *Config file system & Hot-reload.*
  - [ ] 实现 **BossBar** 与 **ActionBar** 自由切换显示。 / *Toggle between BossBar and ActionBar.*

---

## 📝 配置文件 / Config Example

文件位于 `config/clearitems.json`：
```json
{
  "interval": 300,
  "whiteList": [
    "minecraft:diamond",
    "minecraft:netherite_ingot",
    "minecraft:beacon"
  ]
}
