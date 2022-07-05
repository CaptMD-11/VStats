# VStats

<p align="center">
  <img src="images_README/vstatslogo.png" width="570">
</p>

This is the VStats Java library that computes statistics. The functionalities available include one-variable statistics, binomial & geometric distributions, matrix operations, linear regression computations, confidence intervals, significance tests, etc. 

The VStats library is condensed into a JAR file, which can be located in this project. That way, users can import VStats into their Java projects. 

<i>A Python version of this project is currently being developed</i>

<center><a href="https://captmd-11.github.io/blog/statscalculator/" target="_blank">Download VStats JAR</a></center>

## Java

### Importing into Projects 

Instructions on how to install & import VStats into Java projects are available <a target="_blank" href="https://captmd-11.github.io/blog/statscalculator/importingvstatsintoprojects.html">here</a>. 


### How does the user input data? 

It's actually quite simple. All the methods in VStats are <samp>static</samp>, so they can be called without creating VStats objects. The input data to any method should be the parameter. This works similar to the Java Math library. 

### Documentation 

<b><i>If you choose to clone VStats to your computer</i></b>, documentation is provided for all the methods in the library so that users can learn about what each invidual method actually does. This type of  documentation was written using JavaDoc, in order for users to view the method description in a mini window, as shown in the screenshot below. This is only visible when the <samp>VStats.java</samp> file is in the same folder as your source code. 

<p align="center">
  <img src="images_README/javadocmethodtestss.png" width="570">
</p>

<b><i>If not</i></b>, documentation for VStats is available <a target="_blank" href="https://captmd-11.github.io/blog/statscalculator/documentation.html">here</a>. 

## Python

<i>This section will be published once the initial Python version of VStats is complete. </i>

## Version History

### v1.1
- fixed code for computing the test statistic value & p-value for the significance tests. 
- replaced multi-line <samp>String</samp> output with a single line <samp>String</samp> output for the significance tests (with respect to the p-values on the second line). 

### v1.0
- RELEASE

## License 

The VStats library is protected by the <a href="https://github.com/CaptMD-11/VStats/blob/master/LICENSE.txt" target="_blank">GNU GPL</a> license. 
