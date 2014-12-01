ninepatch-convert
=================
[![Build Status](https://travis-ci.org/andresmanz/ninepatch-convert.svg?branch=master)](https://travis-ci.org/andresmanz/ninepatch-convert)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.cethos.tools/ninepatch-convert/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.cethos.tools/ninepatch-convert)
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)
[![Coverage Status](https://img.shields.io/coveralls/andresmanz/ninepatch-convert.svg)](https://coveralls.io/r/andresmanz/ninepatch-convert?branch=master)

Creating ninepatches by hand can be awful, especially if every developer has to create them himself (e.g. if the base images are exported from vector graphics or similar). This tool is optimized for usage in an automatic export system. In my case, that system is a gradle plugin.

Inside the input directory, there has to be a JSON config file called "ninepatches.json" which defines the ninepatch properties of each input image:

```
{
    "ninePatches": {
        "testimg1.png": {
            "xScalingRange": "16-48",
            "yScalingRange": "16-48",
            "xPaddingRange": "18-46",
            "yPaddingRange": "18-46",
        },
        "testimg2.png": {
            "xScalingRange": "14-50",
            "yScalingRange": "14-50",
            "xPaddingRange": "16-48",
            "yPaddingRange": "16-48",
        }
    }
}
```

These filepaths are relative to the input directory. A ninepatch for each image will be created in the original file's directory. E.g. ```buttons/image1.png``` -> ```buttons/image1.9.png```.

Detailed usage docs and maven repo will follow.
