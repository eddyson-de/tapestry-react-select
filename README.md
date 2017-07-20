# tapestry-react-select [![Build Status](https://travis-ci.org/eddyson-de/tapestry-react-select.svg?branch=master)](https://travis-ci.org/eddyson-de/tapestry-react)

[![Greenkeeper badge](https://badges.greenkeeper.io/eddyson-de/tapestry-react-select.svg)](https://greenkeeper.io/)

Use React-Select (https://github.com/JedWatson/react-select) together with Tapestry (http://tapestry.apache.org/).

React-Select does not provide an AMD module (https://github.com/JedWatson/react-select/issues/270), so create our own bundle using rollup.

## Usage


### `build.gradle`:
```groovy
respositories {
  jcenter()
}

dependencies {
  runtime 'de.eddyson:tapestry-react-select:0.2.5'
}

```

See https://github.com/eddyson-de/tapestry-react for integration of React and Tapestry.
