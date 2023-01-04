import {gsap} from 'gsap';
import {ScrollTrigger} from "gsap/ScrollTrigger";
import {TextPlugin} from "gsap/TextPlugin";

gsap.registerPlugin(ScrollTrigger,TextPlugin);
const signatureWordArray = [ "Figure.k;m", "도형;킴"];
let isStopSignatureTransition = false;
window.mobileCheck = function() {
    let check = false;
    (function(a){if(/(android|bb\d+|meego).+mobile|avantgo|bada\/|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|mobile.+firefox|netfront|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\/|plucker|pocket|psp|series(4|6)0|symbian|treo|up\.(browser|link)|vodafone|wap|windows ce|xda|xiino/i.test(a)||/1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\-(n|u)|c55\/|capi|ccwa|cdm\-|cell|chtm|cldc|cmd\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\-s|devi|dica|dmob|do(c|p)o|ds(12|\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\-|_)|g1 u|g560|gene|gf\-5|g\-mo|go(\.w|od)|gr(ad|un)|haie|hcit|hd\-(m|p|t)|hei\-|hi(pt|ta)|hp( i|ip)|hs\-c|ht(c(\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\-(20|go|ma)|i230|iac( |\-|\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\/)|klon|kpt |kwc\-|kyo(c|k)|le(no|xi)|lg( g|\/(k|l|u)|50|54|\-[a-w])|libw|lynx|m1\-w|m3ga|m50\/|ma(te|ui|xo)|mc(01|21|ca)|m\-cr|me(rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\-2|po(ck|rt|se)|prox|psio|pt\-g|qa\-a|qc(07|12|21|32|60|\-[2-7]|i\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\-|oo|p\-)|sdk\/|se(c(\-|0|1)|47|mc|nd|ri)|sgh\-|shar|sie(\-|m)|sk\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\-|v\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\-|tdg\-|tel(i|m)|tim\-|t\-mo|to(pl|sh)|ts(70|m\-|m3|m5)|tx\-9|up(\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|yas\-|your|zeto|zte\-/i.test(a.substr(0,4))) check = true;})(navigator.userAgent||navigator.vendor||window.opera);
    return check;
};
let cursor = gsap.to(".cursor",{
    opacity:0,
    ease:"power2.inOut",
    repeat: -1,
})
let masterTimeline = gsap.timeline({repeat:-1});
let squareGsap = gsap.from("#icon-button #square", {
    x: 85,
    scale:3,
    rotate:-360,
    paused: true,
})
let triangleGsap = gsap.from("#icon-button #triangle", {
    x:202,
    y:3,
    scale:2.5,
    rotate:368,
    paused: true,
})
let circleGsap = gsap.from("#icon-button #circle", {
    x: 305,
    y:11,
    scale:3.1,
    rotate:360,
    paused: true,
    duration: 1,
    ease: "circle"
})
let menuGsap = gsap.to("#icon-button #logo-menu",{
    fill:"#2b2b2b",
    paused: true,


})
let signatureWrapper = document.getElementById("signature-wrapper");
gsap.from(".signature-area",{
    x: "20vw",
    // y: "10vm",
    // height:"300vm",
    width:"100%",
    fontSize:"10vw",
    paddingTop:() => mobileCheck()?"2%":"10%",
    scrollTrigger:{
        trigger:"body",
        start:'top 0',
        end:"top -20",
        scrub: 1,
        onLeave: ({progress, direction, isActive}) => {
            isStopSignatureTransition = true;
            squareGsap.play();
            triangleGsap.play();
            circleGsap.play();
            if(mobileCheck()){
                menuGsap.play();
            }
            signatureWrapper.classList.add("md:w-[200px]", "lg:w-[250px]");

            cursor.pause(1);
        },
        onEnterBack: ({progress, direction, isActive}) => {
            masterTimeline.play();
            isStopSignatureTransition = false;
            squareGsap.reverse();
            triangleGsap.reverse();
            circleGsap.reverse();
            if(mobileCheck()){
                menuGsap.reverse();
            }
            signatureWrapper.classList.remove("md:w-[200px]", "lg:w-[250px]");
            cursor.play();

        }
    }
});

gsap.from(".menu-gap",{
    // onEnter onLeave onEnterBack onLeaveBack
    scrollTrigger:{
        trigger:"body",
        start:'top 0',
        end:"top -20",
        scrub: 1,
        markers:false,
        // pin: ".content-gap2",

    },
    height:"15vw"
})

// gsap.to("#mobile-aside-gap",{
//     scrollTrigger:{
//         trigger:"body",
//         start:'top 0',
//         end:"top -10",
//         scrub: 1,
//         markers:false,
//         // pin: ".content-gap2",
//
//     },
//     height:0
// })

signatureWordArray.forEach(word=>{
    let tl = gsap.timeline({repeat:1, yoyo:true, repeatDelay:1, onComplete : ()=>{

        }});
    tl.to('.text',{duration:1, text:word, onComplete : ()=>{
            if(isStopSignatureTransition){
                masterTimeline.pause();
            }

        }
    });
    masterTimeline.add(tl)
})
let iconButton = document.getElementById("icon-button");
console.log(iconButton)
iconButton.onclick = function(e){
    let sideBarDom = document.getElementById("side-bar");
    sideBarDom.classList.toggle("left-[-250px]")
}
console.log(iconButton)
//
// function openBar(){
//     console.log("openbar")

//
// }
