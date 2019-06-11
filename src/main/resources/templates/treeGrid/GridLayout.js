TreeGridLoaded(
	{
		Cfg: { id:"grid2", MainCol:"B",Sorting:"0",Style:"Material",FullId:"0",NumberId:"1",IdChars:"0123456789",
			ChildPaging:"3",ChildParts:"2",ChildPartLength:"10",RemoveCollapsed:"2",FastGantt:"0",MaxChildren:"20",
			Dragging:"0",Deleting:"0",CanSelect:"0",Selecting:"0"},
		LeftCols: [
		        { Name:"A",RelWidth:"3",Type:"Text",CanEdit:"0"},
			   	{ Name:"B",RelWidth:"6",Type:"Html",CanEdit:"0"},
				{ Name:"C",RelWidth:"4",Type:"Date",CanEdit:"1",Format:"yyyy-MM-dd"}
			   ],
		
		Header: {Kind:"Header", Align:"center",Spanned:"0",A:"图册号",B:"图册名称",C:"供图日期"},
		Toolbar: {Cells:"Reload,ExpandAll,CollapseAll,Columns,Export"}
	}
)
