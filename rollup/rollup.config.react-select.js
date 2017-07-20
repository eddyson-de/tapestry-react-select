import commonjs from 'rollup-plugin-commonjs';
import resolve from 'rollup-plugin-node-resolve';
import babel from 'rollup-plugin-babel';
import uglify from 'rollup-plugin-uglify';
import replace from 'rollup-plugin-replace';

process.env.BABEL_ENV = 'distribution'

export default {
  entry: 'rollup/react-select.js',
  format: 'amd',
  moduleName: 'Select',
  plugins: [
    resolve({
      jsnext: true,
      main: true,
      skip: ['react', 'react-dom', 'prop-types', 'classnames', 'react-input-autosize']
    }),
    replace({
      'propTypes: {': 'propTypes: process.env.NODE_ENV === \'production\' ? {} : {',
      'Async.propTypes =': 'Async.propTypes = (process.env.NODE_ENV === \'production\') ? {} :'

    }),
    replace({
      'process.env.NODE_ENV': JSON.stringify(process.env.NODE_ENV || 'development')
    }),
    commonjs({
    }),
    babel({
      presets: [
        ['es2015', {'modules': false}],
        'react'
      ],
      plugins: ['transform-object-rest-spread'],
      exclude: 'node_modules/**' // only transpile our source code
    }),
    (process.env.NODE_ENV === 'production' && uglify())
  ],
  dest: process.env.NODE_ENV === 'production' ? 'rollup/dist/react-select.min.js' : 'rollup/dist/react-select.js',
  external: [ 'react', 'react-dom', 'prop-types', 'classnames', 'react-input-autosize' ],
};
