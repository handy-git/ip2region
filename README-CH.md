# ip2region

-------------------------------------------------------------------------------

[**🌎English Documentation**](README.md)

-------------------------------------------------------------------------------

一款简单高效的归属地插件

如果本插件帮助到你，请帮忙右上角点个Star就对作者很大的支持了～

## 变量支持:

本插件目前注册以下PAPI变量(需:PlaceholderAPI)

| 变量名                         | 显示内容           | 示例          |
|-----------------------------|----------------|-------------|
| %ip2region_region%          | 总区域            | 中国          |
| %ip2region_national%        | 国家             | 中国          |
| %ip2region_provincial%      | 省              | 上海          |
| %ip2region_municipal%       | 市              | 上海市         |
| %ip2region_serviceProvider% | 运营商            | 电信          |
| %ip2region_ip%              | 玩家当前ip(1.0.8+) | 127.0.0.1   |
| %ip2region_district%        | 区县(1.1.0+)     | 松江区(1.1.0+) |

### PAPI特别说明

> 你可以使用如下指令进行查询对应变量的结果

```
/papi parse me %ip2region_region%
```

```
/papi parse me %ip2region_municipal%
```

## 使用指南

### 数据来源

> 本插件1.1.0版本开始默认有三种方式获取ip归属  
> 如果你有你的想法或渠道也可以让我添加

1. offline 本地数据源模式(只支持ipv4 99%数据准确性)
2. [ipPlus360](https://mall.ipplus360.com/) 在线api模式(支持ipv4/ipv6,付费)
3. [ipApi](https://ip-api.com/) 在线api模式(支持ipv4/ipv6,免费但是有分钟请求次数限制)

### 优缺点分析

| 类型        | 费用 | 数据准确度   | 是否需要网络 | 支持网络      | 支持级别(最高) | 官网                                              |
|-----------|----|---------|--------|-----------|----------|-------------------------------------------------|
| offline   | 免费 | 99%     | 不需     | ipv4      | 城市级      | [官网](https://github.com/lionsoul2014/ip2region) |
| ipPlus360 | 付费 | 99.999% | 需      | ipv4和ipv6 | 区县级      | [官网](https://mall.ipplus360.com/)               |
| ipApi     | 免费 | 99.9%   | 需      | ipv4和ipv6 | 城市级      | [官网](https://ip-api.com/)                       |

### 特别说明

ipPlus360 如需购买,推荐购买城市级  
[ipv4](https://mall.ipplus360.com/pros/IPVFourGeoAPI)
[ipv6](https://mall.ipplus360.com/pros/IPGeoAPI)

如需区县级别的  
请根据官网的api说明自己在config.yml添加如下配置,其他级别自己修改

```
ipPlus360Ipv4Url: "https://api.ipplus360.com/ip/geo/v1/district/"
ipPlus360Ipv6Url: "https://api.ipplus360.com/ip/geo/v1/ipv6/district/"
```

### 测试说明

offline和ipApi模式 config.yml中添加 然后重载插件后测试玩家重新登录

```
testIp: 测试IP
```

ipPlus360模式 config.yml中添加 然后重载插件后测试玩家重新登录(同时只能添加一个,俩个都添加默认执行ipv4)

```
testIp4: 测试IP
testIp6: 测试IP
```

## 本项目访问次数

![Page Views Count](https://badges.toozhao.com/badges/01JHVE4A6W89JQD1KP9D8SPM0J/green.svg)

## GITHUB自动构建版本下载渠道:

[GITHUB ACTIONS](https://github.com/handy-git/ip2region/actions)

## 使用统计

![](https://bstats.org/signatures/bukkit/ip2region.svg)

## Star History

[![Star History Chart](https://api.star-history.com/svg?repos=handy-git/ip2region&type=Date)](https://star-history.com/#handy-git/ip2region&Date)

