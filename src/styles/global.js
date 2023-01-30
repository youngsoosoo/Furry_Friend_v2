import { createGlobalStyle } from 'styled-components';
import tway from '../font/tway.ttf'

const GlobalStyle = createGlobalStyle`
    @font-face {
        font-family: 'tway';
        src: url(${tway}) format('truetype');
    }
`


export default GlobalStyle
