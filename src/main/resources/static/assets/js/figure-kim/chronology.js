import gsap from "gsap";
import {ScrollTrigger} from "gsap/ScrollTrigger";

const yearNav = document.getElementById("year-nav");
const sectionArray = gsap.utils.toArray("section");
const timelineUl = document.getElementById("timeline-ul");
const sectionH1Array = gsap.utils.toArray("section h1");
const scrollTimeline = gsap.timeline()
let liOffsetWidth = 0;
window.gsap = gsap;
sectionH1Array.forEach((value, index) => {
    let year = value.textContent;
    let liTag = document.createElement("li");
    let aTag = document.createElement("a");
    aTag.classList.add("nav__link","relative","block","min-w-[8rem]","pt-[1.5rem]","px-[1rem]","pb-[0.5rem]","text-center","hover:underline","focus:underline","after:content-['']","after:absolute","after:top-0","after:left-1/2","after:w-[0.65rem]","after:h-[0.65rem]","after:rounded-[50%]","after:-translate-x-1/2","after:origin-center","after:bg-custom-black-900")
    // aTag.className = "nav__link relative block min-w-[8rem] pt-[1.5rem] px-[1rem] pb-[0.5rem] text-center hover:underline focus:underline after:content-[''] after:absolute after:top-0 after:left-1/2 after:w-[0.65rem] after:h-[0.65rem] after:rounded-[50%] after:-translate-x-1/2 after:origin-center after:bg-custom-black-900";
    aTag.href = "#"+value.parentElement.id;
    aTag.dataset.link=""
    let spanTag = document.createElement("span");
    spanTag.textContent = year;
    aTag.append(spanTag)
    liTag.append(aTag)
    timelineUl.append(liTag);
    liOffsetWidth = liTag.offsetWidth

});


let gapSize = ()=>{
    let result = (yearNav.offsetWidth - gsap.utils.toArray("#timeline-ul li").map(el => el.offsetWidth)
        .reduce((a, b) => a + b)) / (sectionArray.length - 1);

    console.log("result", result)
    return result > 0 ? result : 0;
}

let allSectionHeightWithoutLast = 0;
let offsetArray = new Array();
offsetArray.push(0)
sectionArray.forEach((el, index )=>{
    if(index == sectionArray.length-1){
    }else{
        allSectionHeightWithoutLast += el.offsetHeight;
        offsetArray.push(el.offsetHeight);
        scrollTimeline.to(yearNav, {x: ()=>"+="+(liOffsetWidth+(gapSize()))* -1, ease: 'none', duration:() => {
            console.log("duration")
            return el.offsetHeight
            }}, ">")
    }
})

console.log(sectionArray.filter((el, i) => i != sectionArray.length - 1).map((el, i) => el.offsetHeight).reduce((acc,cur,index, array)=>{
    // index까지의 합
    let sumToIndex = array.slice(0,index+1).reduce((a,b) => a+b);
    let sectionAllHeight = document.getElementById("section-wrapper").offsetHeight - gsap.utils.toArray("#section-wrapper section")[gsap.utils.toArray("#section-wrapper section").length-1].offsetHeight;
    acc[index + 1] = sumToIndex / sectionAllHeight > 1 ? 1 : sumToIndex / sectionAllHeight;
    // acc
    return  acc;
},[0]));

let triggerEnd = () =>sectionArray.map(i => i.offsetHeight).reduce((a, b) => a + b) - sectionArray[sectionArray.length-1].offsetHeight;
let heightSum = 0;
let snapArray = offsetArray.map(height => {
    heightSum += height;
    return heightSum/allSectionHeightWithoutLast
})

console.log(snapArray)
console.log(sectionArray)
const st = ScrollTrigger.create({
    trigger: document.getElementById("section-wrapper"),
    markers:true,
    start: () => "0 "+ document.getElementById("kim-nav").offsetHeight,
    end: () => triggerEnd()+ " " + document.getElementById("kim-nav").offsetHeight,
    animation: scrollTimeline,
    scrub: true, // sync timeline to scroll position
    snap: {
        snapTo : (value, trigger) => {
            console.log(value,trigger);
            let targetArray = sectionArray.filter((el, i) => i != sectionArray.length - 1).map((el, i) => el.offsetHeight).reduce((acc,cur,index, array)=>{
                let sumToIndex = array.slice(0,index+1).reduce((a,b) => a+b);
                let sectionAllHeight = document.getElementById("section-wrapper").offsetHeight - gsap.utils.toArray("#section-wrapper section")[gsap.utils.toArray("#section-wrapper section").length-1].offsetHeight;
                acc[index + 1] = sumToIndex / sectionAllHeight > 1 ? 1 : sumToIndex / sectionAllHeight;
                // acc
                return  acc;
            },[0])
            let targetIndex = targetArray.findIndex((offset, index) => offset > value);


            let gapWithBefore = value - targetArray[targetIndex - 1];
            let gapWithAfter = targetArray[targetIndex]- value;
            return gapWithAfter > gapWithBefore ? targetArray[targetIndex - 1] : targetArray[targetIndex];


            // return Math.round(value / 0.1) * 0.1
        },
        inertia:false,
        directional:false,
        delay:0.6,
        ease: "Power2.easeOut",
        onComplete:(a) => {

            //중딩 second picture opacity toggle
            console.log(a.scroll()+document.getElementById("kim-nav").offsetHeight, document.getElementById("year-01").offsetTop+1)
            if(Math.abs(a.scroll()+document.getElementById("kim-nav").offsetHeight-document.getElementById("year-01").offsetTop+1)<5){
                console.log("dddd")
                document.getElementById("middle-second-picture").classList.remove("opacity-0")
            }
        },
        onStart:(a) => document.getElementById("middle-second-picture").classList.add("opacity-0"),
        onInterrupt:(a) => console.log("onInterrupt"),
    }
    ,
    // snap: sectionArray.map((el, i) => el.offsetHeight).reduce((acc,cur,index, array)=>{
    //     // index까지의 합
    //     let sumToIndex = array.slice(0,index+1).reduce((a,b) => a+b);
    //     let sectionAllHeight = document.getElementById("section-wrapper").offsetHeight - gsap.utils.toArray("#section-wrapper section")[gsap.utils.toArray("#section-wrapper section").length-1].offsetHeight
    //     acc[index+1] = sumToIndex/sectionAllHeight
    //     // acc
    //     return  acc;
    // },[0]),
    // onSnapComplete: ({progress, direction, isActive}) => console.log(progress, direction, isActive)

})
console.log(scrollTimeline)
console.log(st)




const el1 = document.querySelectorAll("section").item(0);
const tl1 = gsap.timeline()
tl1.from("#receive-me",{
    x:-100,
    y:-500,
    opacity:0,
    duration:1,
    ease:"power2.inOut",
})
const scrollLTrigger1 = ScrollTrigger.create({
    animation:tl1,
    trigger: document.querySelectorAll("section").item(0),
    // markers:true,
    start:"top center",
    end:"top +=100"
})


const tl2 = gsap.timeline()

tl2.set("#_삼",{
    fill: "gray"
})
tl2.set("#_오",{
    fill: "gray"
})
tl2.set("#_삼2",{

    fill: "gray"

})
tl2.set("#_삼-2",{

    fill: "gray"

})
tl2.set("#_오-2",{

    fill: "gray"

})
tl2.set("#_오2",{

    fill: "gray"
})
tl2.set("#_삼오유치원",{
    opacity:"0%"
})
tl2.pause(2)
tl2.to("#_삼", {
    opacity: "100%",
    duration:2,
} )
tl2.to("#_오", {
    opacity: "100%",
    duration:2,
}, "-=2")
tl2.set("#_삼2", {
    opacity: "100%"
})
tl2.set("#_오2", {
    opacity: "100%"
})
tl2.to("#_삼", {
    x: "-56%",
    y: "151%",
    duration:2,
} )
tl2.to("#_오", {
    x: "-195%",
    y: "200%",
    duration:2,
}, "-=2")


tl2.to("#_삼오유치원",{
    opacity:"100%",
    duration:1
})

const scrollLTrigger2 = ScrollTrigger.create({
    animation:tl2,
    trigger: document.querySelectorAll("section").item(1),
    // markers:true,
    start:"center center",
    end:"center +=100"
})
