import gsap from "gsap";
import {ScrollTrigger} from "gsap/ScrollTrigger";

gsap.registerPlugin(ScrollTrigger);
let wordClasses = document.getElementsByClassName("description-word");
let descriptionWrapper = document.getElementById("description");
let noPikaImg = document.getElementById("no-pika-img");
let pikaImg = document.getElementById("pika-img");
let imgWrapper = document.getElementById("index-img-wrapper");
let indexDescriptionWrapper = document.getElementById("index-description-wrapper");
let recommendWrapper = document.getElementById("recommend-wrapper");



// gsap.to(imgWrapper, {
//     scrollTrigger:{
//         trigger:imgWrapper,
//         markers:true,
//         scrub:true,
//         pin:true,
//         pinSpacing: false,
//         anticipatePin:1,
//         start:()=>`${mobileCheck()?"76%":"60%"} top`,
//         end:()=>`+=3000`,
//         onEnter: ({progress, direction, isActive}) => {
//             console.log("onEnter")
//             noPikaImg.classList.add("opacity-0")
//
//         },
//         onLeave:({progress, direction, isActive}) => {
//             console.log("onLeave")
//             // noPikaImg.classList.remove("opacity-0")
//
//         },
//         onLeaveBack:({progress, direction, isActive}) => {
//             console.log("onLeaveBack")
//             noPikaImg.classList.remove("opacity-0")
//
//         },
//         onEnterBack: ({progress, direction, isActive}) => {
//             console.log("onEnterBack")
//             // noPikaImg.classList.add("opacity-0")
//         },
//         onRefresh: ({progress, direction, isActive}) => {
//             console.log("onRefresh")
//             // noPikaImg.classList.add("opacity-0")
//         }
//
//
//
//     },
//     // yPercent: -80,
//     // position: "fixed",
//
//     // opacity: 0,
//
//
//
// })
const tl = gsap.timeline();
tl.from(document.getElementById("description"), {
    duration :1,
    y:`${pikaImg.offsetHeight/2}`
})
document.querySelectorAll(".opacity-target").forEach(span =>{
    tl.from(span,{
        duration :1,
        opacity :0
    }, "-=2")
})
tl.to(noPikaImg,{
    duration: 0.5,
    opacity: 1
}, "-=0.5")
tl.from(document.getElementById("bla-bla-1"),{
    duration:0.5,
    opacity :0
},)
tl.from(document.getElementById("bla-bla-2"),{
    duration:0.5,
    opacity :0
},)
tl.from(document.getElementById("aside-bg"),{
    duration:1,
    opacity:0
}, "+=1")
tl.from(document.getElementById("side-bar"),{
    duration :1,
    opacity:0
}, "+=1")
// tl.from(pikaImg, {opacity:0});
tl.from(recommendWrapper,{
    opacity :0
})

// tl.to(imgWrapper, {
//     // y: ()=>imgWrapper.offsetHeight,
//     duration:1
// }).to(descriptionWrapper, {
//     // scale:2,
//     duration:1,
//     // delay:"-=1"
// }, "-=1")


ScrollTrigger.create({
    animation: tl,
    trigger:indexDescriptionWrapper,
    markers:true,
    scrub:1.5,
    pin:true,
    pinSpacing: true,
    anticipatePin:1,
    // start:()=>`${mobileCheck()?"76%":"60%"} top`,
    start:"-40 top",
    end: "+=500",
    reverse:false,
    // end:()=>`${descriptionWrapper.offsetTop -200}`,
    endTrigger:descriptionWrapper,
    onEnter: ({progress, direction, isActive}) => {
        console.log("onEnter")
        // noPikaImg.classList.add("opacity-0")

    },
    onLeave:({progress, direction, isActive}) => {
        console.log("onLeave")
        // noPikaImg.classList.remove("opacity-0")

    },
    onLeaveBack:({progress, direction, isActive}) => {
        console.log("onLeaveBack")
        // noPikaImg.classList.remove("opacity-0")

    },
    onEnterBack: ({progress, direction, isActive}) => {
        console.log("onEnterBack")
        // noPikaImg.classList.add("opacity-0")
    },
    onRefresh: ({progress, direction, isActive}) => {
        console.log("onRefresh")
        // noPikaImg.classList.add("opacity-0")
    },
});



