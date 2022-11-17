/** @type {import('tailwindcss').Config} */
const { colors: defaultColors } = require('tailwindcss/defaultTheme')

// const { colors: defaultColors } = import ('tailwindcss/defaultTheme')


const colors={
  ...defaultColors,
  ...{
    "green": {
      "100": "#A5EEC5",
      "200": "#5DE097",
      "300": "#39D980",
      "400": "#03CF5D",
      "500": "#0AB456",
      "600": "#13924D",
      "700": "#1C7145",
      "800": "#254F3C",
      "900": "#2A3B37",
    },
    "blue": {
      "100": "#DCEDFF",
      "200": "#98CBFF",
      "300": "#71B8FF",
      "400": "#3197FF",
      "500": "#1882ff",
      "600": "#1076DD",
      "700": "#1863B3",
      "800": "#1F5188",
      "900": "#2B3444",
    },"red": {
      "100": "#FDE0E0",
      "200": "#FCC2C2",
      "300": "#FA9999",
      "400": "#F76666",
      "500": "#F53D3D",
      "600": "#BC383A",
      "700": "#753337",
      "800": "#4A2F34",
      "900": "#382E34",
    },"gray": {
      "100": "#E9E9EC",
      "200": "#C1BFC7",
      "300": "#ACABB4",
      "400": "#9896A2",
      "500": "#838190",
      "600": "#666472",
      "700": "#54525D",
      "800": "#413F48",
      "900": "#2E2D33",
    },"custom-black": {
      "900": "#1D1C22"
    },
    "white":"#FFF"
  }
}
module.exports = {
  content: ['./src/main/resources/**/*.{html,js}'],
  theme: {
    colors:colors,
    container:{
      center: true,

    },
    extend: {
      fontFamily:{
        rix:["rixcitruslife-pro", "sans-serif"],
        whale:["TTWhaleGothic", "sans-serif"]
      }
    },
  },

}
// defaultColors.
console.log()
