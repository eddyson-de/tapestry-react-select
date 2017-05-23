import Select from 'react-select';
import React from 'react';

function defaultChildren (props) {
  return (
      <Select {...props} />
  );
};

// work around https://github.com/rollup/rollup-plugin-commonjs/issues/105,
// https://github.com/JedWatson/react-select/issues/1517,
// https://github.com/JedWatson/react-select/pull/1741
Select.Async.defaultProps = Object.assign({}, Select.Async.defaultProps, {children: defaultChildren}); 

export default Select;