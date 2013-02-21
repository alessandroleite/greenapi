System Monitor [![Build Status](https://travis-ci.org/alessandroleite/system-monitor.png)](https://travis-ci.org/alessandroleite/system-monitor)	       
===================

What is it ?
------------

This is a system to keep track of system resources, such as CPU usage, frequency, temperature and the amount of memory used. Also, it's possible to change the CPU frequency and see its impact in CPU temperature.

How to contribute
--------------

### Reporting a Bug / Requesting a Feature

To report an issue or request a new feature you just have to open an issue in the repository issue tracker (<https://github.com/alessandroleite/system-monitor/issues>).

### Contributing some code

To contribute, follow this steps:

 1. Fork this project
 2. Add the progress label to the issue you want to solve (add a comments to say that you work on it)
 3. Create a topic branch for this issue
 4. When you have finish your work, open a pull request (use the issue title for the pull request title)

Dependencies
--------------

1. [lm-sensors](http://www.lm-sensors.org);
2. [decode-dimms]();
3. [SIGAR](http://support.hyperic.com/display/SIGAR/Home) included in the lib directory;
4. [cpupower](http://doc.opensuse.org/products/draft/SLES/SLES-tuning_sd_draft/cha.tuning.power.html)
5. [iostat](http://linuxcommand.org/man_pages/iostat1.html)

## License 

The project is licensed under the MIT license. 
See License.txt for details.
