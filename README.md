# ip2region

-------------------------------------------------------------------------------

[**🌎中文文档**](README-CH.md)

-------------------------------------------------------------------------------

A simple and efficient geolocation plugin.

If this plugin helps you, clicking the ⭐ Star in the upper right corner would be a great support to the author~

## Placeholder Support: 

This plugin currently registers the following PAPI variables (requires: PlaceholderAPI)

| Variable                    | Description                | Example            |
|-----------------------------|----------------------------|--------------------|
| %ip2region_region%          | Overall region             | China              |
| %ip2region_national%        | Country                    | China              |
| %ip2region_provincial%      | Province                   | Shanghai           |
| %ip2region_municipal%       | City                       | Shanghai City      |
| %ip2region_serviceProvider% | ISP                        | China Telecom      |
| %ip2region_ip%              | Player current IP (1.0.8+) | 127.0.0.1          |
| %ip2region_district%        | District (1.1.0+)          | Songjiang (1.1.0+) |

### Special Notes on PAPI

> You can use the following commands to test variable results:

```
/papi parse me %ip2region_region%
```

```
/papi parse me %ip2region_municipal%
```

## User Guide

### Data Sources

> The plugin currently supports five methods for getting IP geolocation.  
> If you have your own ideas or channels, feel free to let me know for integration.

1. offline - Local data source mode (IPv4/IPv6, ~99% accuracy)
2. [ipPlus360](https://mall.ipplus360.com/) - Online API mode (IPv4/IPv6, paid)
3. [ipApi](https://ip-api.com/) - Online API mode (IPv4/IPv6, free with rate limits)
4. [whois](https://whois.pconline.com.cn/) - Online API mode (IPv4/IPv6, free)
5. [voreApi](https://api.vore.top) - Online API mode (IPv4/IPv6, free)

### Pros and Cons

| Type             | Cost | Accuracy | Network Required | IP Support  | Max Level      | Website                                           |
|------------------|------|----------|------------------|-------------|----------------|---------------------------------------------------|
| offline          | Free | 99%      | No               | IPv4 & IPv6 | City level     | [Site](https://github.com/lionsoul2014/ip2region) |
| ipPlus360        | Paid | 99.999%  | Yes              | IPv4 & IPv6 | District level | [Site](https://mall.ipplus360.com/)               |
| ipApi            | Free | 99.9%    | Yes              | IPv4 & IPv6 | City level     | [Site](https://ip-api.com/)                       |
| whois (1.3.0+)   | Free | 99.9%    | Yes              | IPv4 & IPv6 | District level | [Site](https://whois.pconline.com.cn/)            |
| voreApi (1.3.0+) | Free | 99.9%    | Yes              | IPv4 & IPv6 | District level | [Site](https://api.vore.top)                      |

### Special Note

For offline type starting from plugin version 2.0.0+, data is no longer built-in and needs to be downloaded manually and
placed in the plugins/ip2region directory.

123 Drive: [Click to Access](https://www.123865.com/s/9DpPjv-ysdFh)  
GitHub: [Click to Access](https://github.com/lionsoul2014/ip2region)

If you choose to purchase ipPlus360, city-level data is recommended:  
[IPv4](https://mall.ipplus360.com/pros/IPVFourGeoAPI)  
[IPv6](https://mall.ipplus360.com/pros/IPGeoAPI)

For district-level data,  
please refer to the official API docs and manually add the following to your `config.yml`.  
Modify other levels accordingly.

```
ipPlus360Ipv4Url: "https://api.ipplus360.com/ip/geo/v1/district/"
ipPlus360Ipv6Url: "https://api.ipplus360.com/ip/geo/v1/ipv6/district/"
```

### Testing Instructions

For offline and ipApi modes, add the following in `config.yml` and reload the plugin, then re-login the player:

```
testIp: test IP
```

For ipPlus360 mode, add the following in `config.yml` and reload the plugin, then re-login the player  
(Only one can be set at a time, if both are present, IPv4 will be used by default):

```
testIp4: test IP
testIp6: test IP
```

## Download GitHub CI Builds:

[GITHUB ACTIONS](https://github.com/handy-git/ip2region/actions)

## Usage Statistics

![](https://bstats.org/signatures/bukkit/ip2region.svg)

## Star History

[![Star History Chart](https://api.star-history.com/svg?repos=handy-git/ip2region&type=Date)](https://star-history.com/#handy-git/ip2region&Date)
