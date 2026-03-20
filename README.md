<div align="center">
  <img src="https://raw.githubusercontent.com/Miraitowa-alt/clearitems/main/src/main/resources/icon.png" width="128" height="128" alt="ClearItems Icon">

  # ClearItems - 自动化实体清理 / Automated Entity Cleanup

  [![Minecraft](https://img.shields.io/badge/Minecraft-1.21.x-blue.svg?style=for-the-badge&logo=minecraft)](https://modrinth.com/mod/clearitems)
  [![Platform](https://img.shields.io/badge/Platform-Fabric-lightgrey.svg?style=for-the-badge)](https://fabricmc.net/)
  [![License](https://img.shields.io/badge/License-MIT-green.svg?style=for-the-badge)](LICENSE)
  [![Stars](https://img.shields.io/github/stars/Miraitowa-alt/clearitems?style=for-the-badge&color=yellow)](https://github.com/Miraitowa-alt/clearitems/stargazers)

  **轻量、可视化的生产级 Fabric 清理方案 / Lightweight & Visualized Cleanup for Fabric.**
</div>

---

## 📖 简介 / Introduction

**ClearItems** 是一款专为 Fabric 1.21.x 开发的实体清理模组。它结合了直观的倒计时系统与灵活的白名单管理，旨在为服务器提供高效优化的同时，确保玩家物资安全。

**ClearItems** is a high-performance cleanup mod for Fabric 1.21.x. It combines an intuitive countdown system with flexible whitelist management.

---

## ✨ 核心特性 / Key Features

* **📊 全程可视化 / Full Visualization** :
  支持使用 **BossBar** 或 **ActionBar** 实时显示彩色倒计时。根据剩余时间自动切换颜色（绿色 > 黄色 > 红色加粗），不遮挡战斗视线，完美兼容重型整合包。
  *Supports **BossBar** or **ActionBar** real-time countdown with dynamic colors (Green > Yellow > Bold Red). Avoids HUD clutter and overlaps.*

* **🛡️ 智能动态白名单 / Smart Dynamic Whitelist** :
  支持在游戏内实时管理受保护物品（如钻石、附魔书）。被加入白名单的物品将永远不会被自动清理。
  *Manage protected items (e.g., diamonds, enchanted books) in-game via commands. Whitelisted items are never cleared.*

* **📈 精准清理战报 / Precise Reporting** :
  精确统计并通报掉落物的**具体总个数**（如：一组 64 个物品计为 64 个），让优化效果一目了然。
  *Accurately counts total item stacks (e.g., 64 items counted as 64, not 1 entity) for clear feedback.*

* **🚀 极简性能开销 / Minimal Overhead** :
  基于原生 Fabric API 构建，针对重度混淆环境深度优化，确保秒速启动与极端稳定性。
  *Built on native Fabric API; optimized for heavily obfuscated environments and maximum uptime.*

---

## ⭐ 支持本项目 / Support the Project

如果您觉得 **ClearItems** 对您的服务器有所帮助，请考虑在 GitHub 上点亮一颗小星星！您的支持是我持续更新的最大动力。

<div align="center">
  <a href="https://github.com/Miraitowa-alt/clearitems">
    <img src="https://img.shields.io/badge/Support-Give%20a%20Star-yellow.svg?style=for-the-badge&logo=github" alt="Give a Star">
  </a>
</div>

---

## 🎮 指令指南 / Commands

| 指令 (Command) | 说明 (Description) | 示例 (Example) |
| :--- | :--- | :--- |
| `set <seconds>` | 修改自动清理的时间间隔 / Set interval | `/clearitems set 600` |
| `add <item_id>` | 将物品加入白名单 / Add item to whitelist | `/clearitems add minecraft:diamond` |
| `remove <id>` | 将物品从白名单移除 / Remove from whitelist | `/clearitems remove minecraft:stone` |
| `list` | **[New]** 查看当前白名单 / View whitelist | `/clearitems list` |
| `clear` | 立即执行清理并发布战报 / Immediate cleanup | `/clearitems clear` |
| `start / stop` | 开启或停止自动清理逻辑 / Toggle auto-cleanup | `/clearitems stop` |

---

## 🔑 权限节点 / Permissions

本模组已移除硬编码的 OP 权限限制。在公网服务器上，请务必配合 **LuckPerms** 使用以下节点：

* `clearitems.admin`: 拥有所有指令权限 / Full access to all commands.
* `clearitems.list`: 仅允许查看白名单 / Permission to view the whitelist.

---

## 🗺️ 开发路线图 / Roadmap

- [x] **v1.1.1 (Current)**: 专注于 ActionBar 显示、稳定性修复与 `/clearitems list` 功能。
- [ ] **v1.2.0 (Planned)**: 
  - [ ] 引入 `config.json` 配置文件系统与热加载 / Config file system & Hot-reload.
  - [ ] 实现 **BossBar** 与 **ActionBar** 自由切换显示 / Toggle between BossBar and ActionBar.
- [ ] **v2.0.0 (Long-term Milestone)**: 
  - [ ] **[Smart Cleanup]** 根据服务器当前 TPS 自动调整清理频率。 / *Auto-adjust cleanup interval based on real-time server TPS.*
  - [ ] **[Entity Merging]** 实验性功能：自动合并附近的掉落物实体以减轻负担。 / *Experimental: Auto-merge nearby item entities to reduce lag.*

---

## ⚖️ 开源协议 / License

本项目采用 **MIT License** 开源。
Licensed under the **MIT License**.

---

<div align="center">

感谢所有使用本模组的玩家与开发者！

<i>Thank you to all players and developers using this mod!</i>

<br>

<i>Built with ❤️ by <a href="https://github.com/Miraitowa-alt">Miraitowa-alt</a></i>

</div>
