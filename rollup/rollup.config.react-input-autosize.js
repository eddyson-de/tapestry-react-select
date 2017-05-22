import commonjs from 'rollup-plugin-commonjs';
import resolve from 'rollup-plugin-node-resolve';
import babel from 'rollup-plugin-babel';
import uglify from 'rollup-plugin-uglify';
import replace from 'rollup-plugin-replace';

process.env.BABEL_ENV = 'distribution'

export default {
  entry: 'rollup/react-input-autosize.js',
  format: 'amd',
  moduleName: 'AutosizeInput',
  plugins: [
    resolve({
      jsnext: true,
      main: true,
      skip: ['react', 'prop-types']
    }),
    commonjs({
      
    }),
    replace({
      'process.env.NODE_ENV': JSON.stringify(process.env.NODE_ENV || 'development')
    }),
    babel({
      presets: [
        ['es2015', {'modules': false}],
      ],
      exclude: 'node_modules/**' // only transpile our source code
    }),
    (process.env.NODE_ENV === 'production' && uglify())
  ],
  dest: process.env.NODE_ENV === 'production' ? 'rollup/dist/react-input-autosize.min.js' : 'rollup/dist/react-input-autosize.js',
  external: [ 'react', 'prop-types' ]
};
