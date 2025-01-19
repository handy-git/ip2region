# ip2region

-------------------------------------------------------------------------------

[**🌎中文文档**](README-CH.md)

-------------------------------------------------------------------------------

A simple and efficient geolocation plugin.

If this plugin has helped you, please consider giving it a Star in the upper right corner—it would greatly support the
author!

## Variable Support

This plugin currently registers the following PAPI variables (requires: PlaceholderAPI):

| Variable Name               | Display Content      | Example   |
|-----------------------------|----------------------|-----------|
| %ip2region_region%          | Full Region          | China     |
| %ip2region_national%        | Country              | China     |
| %ip2region_provincial%      | Province             | Shanghai  |
| %ip2region_municipal%       | City                 | Shanghai  |
| %ip2region_serviceProvider% | ISP                  | Telecom   |
| %ip2region_ip%              | Player's IP (1.0.8+) | 127.0.0.1 |
| %ip2region_district%        | District (1.1.0+)    | Songjiang |

### PAPI Special Instructions

> Use the following command to query the results for specific variables:

```
/papi parse me %ip2region_region%
```

```
/papi parse me %ip2region_municipal%
```

## User Guide

### Data Sources

Starting from version 1.1.0, this plugin provides three methods to retrieve IP geolocation data.  
If you have other ideas or sources, feel free to suggest them!

1. offline - Local data source mode (IPv4 only, 99% accuracy)
2. [ipPlus360](https://mall.ipplus360.com/) - Online API mode (supports IPv4/IPv6, paid)
3. [ipApi](https://ip-api.com/) - Online API mode (supports IPv4/IPv6, free but with request limits per minute)

### Pros and Cons Analysis

| Type      | Cost | Data Accuracy | Requires Network | Supported Network | Highest Level Supported | Official Site                                              |
|-----------|------|---------------|------------------|-------------------|-------------------------|------------------------------------------------------------|
| offline   | Free | 99%           | No               | IPv4              | City level              | [Official Site](https://github.com/lionsoul2014/ip2region) |
| ipPlus360 | Paid | 99.999%       | Yes              | IPv4 and IPv6     | District level          | [Official Site](https://mall.ipplus360.com/)               |
| ipApi     | Free | 99.9%         | Yes              | IPv4 and IPv6     | City level              | [Official Site](https://ip-api.com/)                       |

### Special Note

If you wish to purchase ipPlus360, it is recommended to buy the city-level option.  
[IPv4](https://mall.ipplus360.com/pros/IPVFourGeoAPI) | [IPv6](https://mall.ipplus360.com/pros/IPGeoAPI)

For district-level support, add the following configuration to config.yml according to the official API documentation,
and modify for other levels as needed:

```
ipPlus360Ipv4Url: "https://api.ipplus360.com/ip/geo/v1/district/"
ipPlus360Ipv6Url: "https://api.ipplus360.com/ip/geo/v1/ipv6/district/"
```

### Testing Instructions

For offline and ipApi modes, add to config.yml, reload the plugin, and re-login to test.

```
testIp: test IP address
```

For ipPlus360 mode, add to config.yml, reload the plugin, and re-login to test (only one can be added at a time; if both
are added, IPv4 is prioritized).

```
testIp4: test IPv4 address
testIp6: test IPv6 address
```

## Page Views Count

![Page Views Count](https://badges.toozhao.com/badges/01JHVE4A6W89JQD1KP9D8SPM0J/green.svg)

## GITHUB Automatic Build Download Channel:

[GITHUB ACTIONS](https://github.com/handy-git/ip2region/actions)

## Usage Statistics

![](https://bstats.org/signatures/bukkit/ip2region.svg)

## Star History

[![Star History Chart](https://api.star-history.com/svg?repos=handy-git/ip2region&type=Date)](https://star-history.com/#handy-git/ip2region&Date)

## gitSponsors

[<img src="https://api.gitsponsors.com/api/badge/img?id=550263180" height="20">](https://api.gitsponsors.com/api/badge/link?p=plVBiIaCrQ+yGb6hPNP8H+8YhPeaEAOIaOeA69o4K0M7HKvozxAV41ckkeMd8DpY91VVJ/ULd4vlhG4Z8jipQQ==)
