import commonjs from 'rollup-plugin-commonjs';
import resolve from 'rollup-plugin-node-resolve';
import babel from 'rollup-plugin-babel';
import uglify from 'rollup-plugin-uglify';
import replace from 'rollup-plugin-replace';

process.env.BABEL_ENV = 'distribution'

const isProduction = process.env.NODE_ENV === 'production';

export default {
  name: 'Select',
  input: 'rollup/react-select.js',
  output: {
    file: process.env.NODE_ENV === 'production' ? 'rollup/dist/react-select.min.js' : 'rollup/dist/react-select.js',
    format: 'amd'
},
  plugins: [
    resolve({
      jsnext: false,
      main: true,
      skip: ['react', 'react-dom', 'prop-types', 'classnames', 'react-input-autosize']
    }),
    replace({
      'propTypes: {': 'propTypes: process.env.NODE_ENV === \'production\' ? {} : {'
    }),
    {
      transform: (source,id)=>{
        if (isProduction){
          let transformedSource = source.replace(/\.propTypes =/g, '.propTypes = (process.env.NODE_ENV === \'production\') ? {} :');
          transformedSource = transformedSource.replace(/var propTypes = /g, 'var propTypes = true ? null : ');
          transformedSource = transformedSource.replace(/var stringOrNode = /g, 'var stringOrNode = true ? null : ');
          return transformedSource;
        } else {
          return source;
        }
      }
    },
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
    isProduction && uglify()
  ],
  external: [ 'react', 'react-dom', 'prop-types', 'classnames', 'react-input-autosize' ],
};
