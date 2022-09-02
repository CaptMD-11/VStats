# VStats

<p align="center">
  <img src="images_README/vstatslogo.png" width="570">
</p>

VStats is a highly optimized Java library that features statistical models such as binomial & geometric distributions, linear regression, confidence intervals, significance tests, and much more. Additional methods, like matrix computations, are also included. 

The VStats library is condensed into a JAR file, which can be located in this project. That way, users can import VStats into their Java projects. 

<br>

<center><b><i>***A Python version of this project is currently being developed***</i></b></center>
<br>
<br>
<center><a href="https://captmd-11.github.io/blog/statscalculator/" target="_blank">Download VStats JAR</a></center>

<br>

## Java

### Importing into Projects 

Instructions on how to install & import VStats into Java projects are available <a target="_blank" href="https://captmd-11.github.io/blog/statscalculator/importingvstatsintoprojects.html">here</a>. 


### How does the user input data? 

It's actually quite simple. All the methods in VStats are <samp>static</samp>, so they can be called without creating VStats objects. The input data to any method should be the parameter. This works similar to the Java Math library. 

### Documentation 

Java documentation for VStats is available <a target="_blank" href="https://captmd-11.github.io/blog/statscalculator/VStats.html">here</a>. 

## Python

<i>This section will be published once the initial Python version of VStats is complete. </i>

## Version History

### v1.6
- added methods for computing one-sample significance tests using the t-distribution. 

### v1.5
- added methods for computing the PDF and CDF of Student's $t$-distribution. 

### v1.4 
- added $\chi^2$ tests and bug fixes for gamma function method. 

### v1.3
- added methods for computing the gamma function, $\chi^2$ PDF, and $\chi^2$ CDF. 

### v1.2
- fixed code for 0 and 1 boundary error for the inverse normal distribution approximation method. 

### v1.1
- fixed code for computing the test statistic value & p-value for the significance tests. 
- replaced multi-line <samp>String</samp> output with a single line <samp>String</samp> output for the significance tests (with respect to the p-values on the second line). 

### v1.0
- RELEASE

## License 

The VStats library is protected by the <a href="https://github.com/CaptMD-11/VStats/blob/master/LICENSE.txt" target="_blank">GNU GPL</a> license. 
