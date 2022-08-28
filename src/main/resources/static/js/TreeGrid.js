var treeGridInfo =  {
    "Panel":{"Copy":"7","Width":"110","Formula":"GetRowPanel(Grid,Row,Col)","OnClickPanelEdit":"EditFile(Row)" ,"OnClickPanelShow":"DisplayFile(Row)",
        "PanelSelectTip":"选择" ,"PanelCopyTip":"复制行","PanelDeleteTip":"删除行"
    },
    "LeftCols":[
        {"Type":"Text","CanEdit":"1","Width":80,"Name":"WBS_SHORT_NAME"},
        {"Type":"Text","CanEdit":"1","Width":250,"Name":"WBS_NAME"}],
    "Cols":[
        {"Enum":"|是|否","Type":"Enum","CanEdit":"1","EnumKeys":"|Y|N","Width":80,"Name":"IS_TASK"},
        {"Type":"Text","CanEdit":"1","Width":80,"Name":"SCH_CNT"},
        {"Type":"Text","CanEdit":"0","Width":80,"Name":"WEIGHT_CNT"},
        {"Type":"Date","Format":"yyyy-MM-dd","CanEdit":"1","Width":120,"Name":"PLAN_START_DTM"},
        {"Type":"Date","Format":"yyyy-MM-dd","CanEdit":"1","Width":120,"Name":"PLAN_END_DTM"},
        {"Type":"Date","Format":"yyyy-MM-dd","CanEdit":"0","Width":120,"Name":"ACT_START_DTM"},
        {"Type":"Date","Format":"yyyy-MM-dd","CanEdit":"0","Width":120,"Name":"ACT_END_DTM"},
        {"Type":"Text","CanEdit":"1","Width":0,"Name":"PARENT_WBS_ID"},
        {"Visible":0,"Name":"R"},
        {"Type":"Float","CanEdit":"1","Width":0,"Name":"COMPLETE_CNT"},
        {"Type":"Html","CanEdit":"0","Width":200,"Name":"FZUSR_ID"},
        {"Type":"Text","CanEdit":"1","Width":0,"Name":"SEQ_NUM"},
        {"Enum":"|设计|采购|施工|调试|质量|安环|其他","Type":"Enum","CanEdit":"1","EnumKeys":"|01|02|03|04|05|06|07","Width":80,"Name":"TASK_TYP"},
        {"Enum":"|项目部|采购部|技术部|市场部|财务部|风控部|领导层","Type":"Enum","CanEdit":"1","EnumKeys":"|01|02|03|04|05|06|07","Width":80,"Name":"ZXCST_TYP"},
        {"Enum":"|是|否","Type":"Enum","CanEdit":"1","EnumKeys":"|Y|N","Width":80,"Name":"MILESTONE_TYP"},
        {"GanttStrict":"1","GanttDescendants":"R","GanttCorrectDependenciesFixed":"1","GanttAllDependencies":"5",
            "GanttWidthEx":"39.2","Name":"G","GanttUnits":"M","GanttPaging":"1","GanttClass":"Blue","Type":"Gantt",
            "GanttCorrectDependencies":"0","GanttStart":"PLAN_START_DTM","GanttHeader2":"M#MMM","GanttHeader1":"M3#MMMMM. yyyy",
            "GanttBackground":";M3#1/1/2008","GanttComplete":"COMPLETE_CNT","GanttEnd":"PLAN_END_DTM","GanttPagingFixed":"1",
            "GanttChartRound":"M6","GanttWidth":"40"}],
    "Head":{"Filter":{"Spanned":0,"IS_TASK":""}},
    "Header":{"WBS_NAME":"任务名称","COMPLETE_CNT":"完成比","WEIGHT_CNT":"权重","PARENT_WBS_ID":"上级节点","Align":"center","SEQ_NUM":"排序",
            "PLAN_END_DTM":"计划完成日期","PLAN_START_DTM":"计划开始日期","ACT_END_DTM":"实际完成日期","Spanned":"1","MILESTONE_TYP":"里程碑",
            "IS_TASK":"作业项","ZXCST_TYP":"任务执行部门","TASK_TYP":"任务类型","Kind":"Header","WBS_SHORT_NAME":"任务编码",
            "ACT_START_DTM":"实际开始日期","FZUSR_ID":"负责人","SCH_CNT":"工期"},
    "Def":[{"Calculated":"1","Name":"parent"},
        { "Name":"R" ,"Panel":"Select,Delete,Copy,Edit,Show" ,"PanelEditWidth":"19","PanelEmptyWidt":"19","PanelEdit":"/images/TreeGrid/add_eps.png",
            "PanelEditTip":"添加eps","PanelShow":"/images/TreeGrid/add_wbs.png","PanelShowTip":"添加wbs"},
        {"Name":"Dir","Panel":"Select,Delete,Copy" ,"PanelFormula":"",
            "PanelPanelSelectTip":"Select directory","PanelPanelCopyTip":"Insert new file into the directory","PanelPanelDeleteTip":"Delete directory"},
        { "Name":"C","LocaleCompare":"1","CaseSensitive":"0"}
        ],
    "Cfg":{"FastGantt":"0","MainCol":"WBS_NAME","Editing":"1","ChildPartLength":"10","Selecting":"1","NumberId":"1",
        "IdChars":"0123456789","Sorting":"0","ChildPaging":"3","RemoveCollapsed":"2","Dragging":"1","Style":"White",
        "id":"PROJWBS","FullId":"0","Deleting":"1","ChildParts":"2","FilterEmpty":"1"},

    "Toolbar":{"Cells":"ExpandAll,CollapseAll,Columns,Export,Reload"},
    "Languages":{"Code":"CNY"},

    "Body":body.Body
}

function EditFile(Row) {

}

function DisplayFile(Row) {

}
function GetRowPanel(G,row,col){
    return "Select,Delete,Copy,Edit,Show";
}

TreeGrid({Debug:'',Data:{Data: treeGridInfo}},"customTreeGrid");
/*

var treeGridUtils = {
    create: function(pkValue, func, staControl){
        var typ = lui.page.getProgramType(lui.page.getProgramId());
        if(typ=='G'||typ=='M'){
            if(!pkValue){
                pkValue = lui.dom.getRootTablePkValue();
            }
        }
        //初始化的时候设置页面未修改
        lui.page.setModified(false);
        var wfSta = "00";
        //获取状态字段值
        if(lui.dom.getRootTableName()&&lui.dom.getTableAttr(lui.dom.getRootTableName(),'stateFieldName')){
            wfSta = lui.dom.getDomValue(lui.dom.getRootTableName(),lui.dom.getTableAttr(lui.dom.getRootTableName(),'stateFieldName'));
        }
        var data = lui.getResult('TreeGridAjax@getTreeGridConfig',{wfSta:wfSta});
        var params = { gridNo:data.gridNo, pkValue:pkValue,	pId:'-1' };
        if(Grids.length==0){
            var treeGirdData = {}
            if(func){
                a = func(params);
                data.dataObj['Body'] = a.Body;
                var g = TreeGrid({Debug:'',Data:{treeGridInfo}},"customTreeGrid");
            }else{
                var g =
            }
        }else{
            if(func){
                a = func(params);
                data.dataObj['Body'] = a.Body;
                Grids[0].Data.Data.Data = data.dataObj;
                Grids[0].Reload(0);
            }else{
                Grids[0].Reload(0);
            }
        }
        Grids[0].Source.Upload.Format="JSON";
    },
    getCurrPageChanges:function(){
        G = Grids[0];
        var changes = JSON.parse(G.GetChanges());
        var changeData = changes.Changes;

        var add = changeData.filter(function(item){return item.Added=="1"});
        var update = changeData.filter(function(item){return item.Changed=="1"});
        var del = changeData.filter(function(item){return item.Devared=="1"});
        var params = {add:add,update:update,del:del}
        return params;
    }

}

var checkDevare = false;
var devareNos = "";
var devareRow = [];
Grids.OnCanRowDevare = function(G, row, type){
    devareNos += row.id+",";
    devareRow.push(row);
    if(!checkDevare){
        checkDevare = true;
        lui.modal.confirm('确定删除节点及其下层节点？', function(obj){
            lui.getResult('TreeGridAjax@devareRows',{devareNos:devareNos,tableName:G.id},function(obj){
                if(obj=="true"){
                    for(i in devareRow){
                        G.DelRow (devareRow[i]);
                    }
                    lui.message.success("删除成功");
                }
                devareNos = "";
                devareRow = [];
                checkDevare = false;
            });
        }, function(){
            devareNos = "";
            devareRow = [];
            checkDevare = false;
        }, '删除节点');
    }
}







*/

















