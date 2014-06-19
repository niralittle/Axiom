
$(document).ready(function() {
    $('.tabs .tab-links a').on('click', function(e) {
        var currentAttrValue = $(this).attr('href');

        // Show/Hide Tabs
        $('.tabs ' + currentAttrValue).slideDown(400).siblings().slideUp(400);

        // Change/remove current tab to active
        $(this).parent('li').addClass('active').siblings().removeClass('active');

        e.preventDefault();
    });
});

function OnOff(obj)
		{
			var
				DivOn,
				DivOff,
				Ctrl;
		
			switch(obj.id)
			{
				case "user" :
				{
					DivOn="forUser";
					DivOff="forStartup";
					break;
				}
				case "startup" :
				{
					DivOn="forStartup";
					DivOff="forUser";
					break;
				}
			}
	
			if(Ctrl=document.getElementById(DivOn))
				Ctrl.style.display="block";
			if(Ctrl=document.getElementById(DivOff))
				Ctrl.style.display="none";
			DivOff="Result";
			if(Ctrl=document.getElementById(DivOff))
				Ctrl.style.display="none";	
			
		}
function Find(obj)
		{
			var
				DivOn,
				Ctrl;
		
			DivOn="Result";
			
			if(Ctrl=document.getElementById(DivOn))
				Ctrl.style.display="block";

		}		
function g(img,flag)
		{
			var
				Ctrl;
			if(Ctrl=document.getElementById("logo"))	
				if (flag=0){
					document.img.border="8px solid #CAD958";
					flag=1;
				}
				else{
					document.img.border="4px solid #CAD958";
					flag=0;
				}
			
		}