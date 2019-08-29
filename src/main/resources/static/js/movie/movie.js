$(document).ready(function(){
    $("#videoDiv").height($(window).height()-10);
    //  $("#tbBody").width($(window).width());
});

var myVideo=document.getElementById("vid");
function playPause()
{
    if (myVideo.paused)
        myVideo.play();
    else
        myVideo.pause();
}

function makeBig()
{
    $("#vid").height($("videoDiv").height()*0.98);
    $("#vid").width($("videoDiv").width()*0.9);
}

function makeSmall()
{
    $("#vid").height($("videoDiv").height()*0.7);
    $("#vid").width($("videoDiv").width()*0.7);
}

function makeNormal()
{
    $("#vid").height($("videoDiv").height()*0.3);
    $("#vid").width($("videoDiv").width()*0.3);
}