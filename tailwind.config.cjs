/** @type {import('tailwindcss').Config} */
const { colors: defaultColors } = require('tailwindcss/defaultTheme')
const colors={
  ...defaultColors,
  ...{
    "custom-black": {
      "900": "#222222"
    }
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
        rix:["rixcitruslife-pro", "sans-serif"]
      }
    },
  },
  // plugins: [require('flowbite/plugin')]

}
