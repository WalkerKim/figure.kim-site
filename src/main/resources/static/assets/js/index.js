import gsap from "gsap";
import {ScrollTrigger} from "gsap/ScrollTrigger";
gsap.registerPlugin(ScrollTrigger);
console.log(ScrollTrigger)
gsap.to("#index-description",{

    scrollTrigger:{
        trigger:"#index-description",
        markers: true,
        start:"top 80%",
        end:"top 40%",
        scrub: 2,
    },
    columnGap:"0rem"
})
