//Query Generation JS
/*************************************options region*********************************************************************/
/*var dbString='{"public":{"sb_user":{"name":"character varying","alias":"character varying","id":"integer"},"testtable":{"Name":"ARRAY","link_id":"integer"},"tablink":{"created_dt":"text","user_id":"integer","link_url":"text","link_name":"text","link_id":"integer"}},"poc":{"csa_customer_dtls":{"year":"integer","true_cost":"numeric","customer_name":"character varying","customer_region":"character varying","engine_model":"character varying","last_shop_visit":"date","cust_id":"integer"}}}';*/
//var dbString = '{ "public": { "formula_group_master_tab": { "formula_grp_mstr_seq_id": "integer", "formula_group_name": "character varying", "formula_group_desc": "character varying" }, "formual_detail_new221": { "fomula_id": "integer", "group_formula_group_id": "integer", "formula_name": "character varying", "formula_text": "character varying" }, "employee": { "eid": "integer", "ename": "character varying", "department_id": "integer", "deg": "character varying", "salary": "double precision" }, "formual_detail_new": { "fomula_id": "integer", "group_formula_group_id": "integer", "formula_name": "character varying", "formula_text": "character varying" }, "formual_detail_new2228": { "fomula_id": "integer", "group_formula_group_id": "integer", "formula_name": "character varying", "formula_text": "character varying" }, "formual_detail_new2227": { "fomula_id": "integer", "group_formula_group_id": "integer", "formula_name": "character varying", "formula_text": "character varying" }, "formual_detail_new2226": { "fomula_id": "integer", "group_formula_group_id": "integer", "formula_name": "character varying", "formula_text": "character varying" }, "formual_group": { "formula_group_id": "integer", "formula_group_name": "character varying" }, "formual_detail_new2225": { "fomula_id": "integer", "group_formula_group_id": "integer", "formula_name": "character varying", "formula_text": "character varying" }, "formual_detail": { "fomula_id": "integer", "group_formula_group_id": "integer", "formula_name": "character varying", "formula_text": "character varying", "post_db_qry_obj_nm": "character varying" }, "formual_group_new221": { "formula_group_id": "integer", "formula_group_name": "character varying" }, "formual_group_new2226": { "formula_group_id": "integer", "formula_group_name": "character varying" }, "formual_detail_new2220": { "fomula_id": "integer", "group_formula_group_id": "integer", "formula_name": "character varying", "formula_text": "character varying" }, "formual_group_new2225": { "formula_group_id": "integer", "formula_group_name": "character varying" }, "formual_group_new2228": { "formula_group_id": "integer", "formula_group_name": "character varying" }, "FormulaGroup": { "formula_grp_mstr_seq_id": "integer", "formula_group_name": "character varying", "formula_group_desc": "character varying" }, "formual_group_new2227": { "formula_group_id": "integer", "formula_group_name": "character varying" }, "department": { "name": "character varying", "id": "integer" }, "formual_detail_new2224": { "fomula_id": "integer", "group_formula_group_id": "integer", "formula_name": "character varying", "formula_text": "character varying" }, "formual_detail_new2223": { "fomula_id": "integer", "group_formula_group_id": "integer", "formula_name": "character varying", "formula_text": "character varying" }, "formual_group_new": { "formula_group_id": "integer", "formula_group_name": "character varying" }, "player": { "name": "character varying", "id": "integer", "startdate": "date" }, "sb_user": { "name": "character varying", "alias": "character varying", "id": "integer" }, "formual_group_new2220": { "formula_group_id": "integer", "formula_group_name": "character varying" }, "formulagroup": { "name": "character varying", "id": "integer" }, "sports": { "name": "character varying", "id": "integer", "startdate": "date" }, "formual_group_new2224": { "formula_group_id": "integer", "formula_group_name": "character varying" }, "formual_group_new1212": { "formula_group_id": "integer", "formula_group_name": "character varying", "start_date": "character varying" }, "formual_group_new2223": { "formula_group_id": "integer", "formula_group_name": "character varying" }, "formual_detail_new1": { "fomula_id": "integer", "group_formula_group_id": "integer", "formula_name": "character varying", "formula_text": "character varying" }, "formual_detail_new212": { "fomula_id": "integer", "group_formula_group_id": "integer", "formula_name": "character varying", "formula_text": "character varying" }, "groups": { "name": "character varying", "id": "integer" }, "formual_detail_new2": { "fomula_id": "integer", "group_formula_group_id": "integer", "formula_name": "character varying", "formula_text": "character varying" }, "formulamaster": { "formulagroup_id": "integer", "formula_name": "character varying", "id": "integer", "formula_text": "character varying" }, "formula_grp_tab": { "formula_grp_seq_id": "integer", "formula_grp_nm": "character varying" }, "formula_group": { "name": "character varying", "id": "integer" }, "professor": { "name": "character varying", "id": "integer", "startdate": "date" }, "testtable": { "Name": "ARRAY" }, "post_db_sql_qry": { "fomula_id": "integer", "group_formula_group_id": "integer", "formula_name": "character varying", "formula_text": "character varying", "post_db_qry_obj_nm": "character varying" }, "csa_det_formula_mstr": { "firstname": "character varying", "formula_name": "character varying", "default_value": "numeric", "formula_mstr_seq_id": "bigint", "engine_model": "character varying", "formula_txt": "character varying", "lastname": "character varying" }, "formual_group_new21": { "formula_group_id": "integer", "formula_group_name": "character varying" }, "formual_group_new22": { "formula_group_id": "integer", "formula_group_name": "character varying" }, "formual_detail_new22": { "fomula_id": "integer", "group_formula_group_id": "integer", "formula_name": "character varying", "formula_text": "character varying" }, "formual_detail_new21": { "fomula_id": "integer", "group_formula_group_id": "integer", "formula_name": "character varying", "formula_text": "character varying" }, "formual_group_new2": { "formula_group_id": "integer", "formula_group_name": "character varying" }, "formual_group_new1": { "formula_group_id": "integer", "formula_group_name": "character varying" }, "tablink": { "created_dt": "text", "user_id": "integer", "link_url": "text", "link_name": "text", "link_id": "integer" }, "customer": { "firstname": "character varying", "formula_name": "character varying", "default_value": "numeric", "formula_mstr_seq_id": "bigint", "engine_model": "character varying", "formula_txt": "character varying", "lastname": "character varying" } }, "poc": { "csa_customer_dtls": { "year": "integer", "true_cost": "numeric", "customer_name": "character varying", "customer_region": "character varying", "engine_model": "character varying", "last_shop_visit": "date", "cust_id": "integer" } } }';
console.log('testing db string');
console.log(dbString);
var dbObject=dbString;
dataBaseList = document.getElementById('data-base-list');
tableList = document.getElementById('ripos');
attrList = document.getElementById('show-me');
conditionAttrList = document.getElementById('condition-attr');
var whereIndex=0;

for(dbs in dbObject) //db names are auto generated
{
	var opt = document.createElement('option');
	opt.value = dbs;
	opt.innerHTML = dbs;
	dataBaseList.appendChild(opt);
}
//selecting db and listing out the corresponding tables
function selectDb(){
	dbName=dataBaseList.value;
	if(tableList.hasChildNodes()){
		while (tableList.firstChild) {
			tableList.removeChild(tableList.firstChild);
		}
	}
	for(tables in dbObject[dbName]){
		var opt = document.createElement('option');
		opt.value = tables;
		opt.innerHTML = tables;
		tableList.appendChild(opt);
	}
}
//selecting the table and listing out the correponding attributes
function selectTable(){
	var dataBase = this.dataBaseList.value;
	if(attrList.hasChildNodes()){
		while (conditionAttrList.firstChild) {
			if(conditionAttrList.hasChildNodes())
			conditionAttrList.removeChild(conditionAttrList.firstChild);
			if(attrList.hasChildNodes())
			attrList.removeChild(attrList.firstChild);
		}
	}
	for (tb=0;tb<getQueryForm.ripos.options.length;tb++){
		if (getQueryForm.ripos.options[tb].selected){
			for(attrs in dbObject[dataBase][getQueryForm.ripos.options[tb].text]){
				var opt3 = document.createElement('option');
				var opt1 = document.createElement('option');
				opt3.value = dbObject[dataBase][getQueryForm.ripos.options[tb].text][attrs];
				opt1.value = dbObject[dataBase][getQueryForm.ripos.options[tb].text][attrs];
				opt3.innerHTML = attrs;
				opt1.innerHTML = attrs;
				opt3.setAttribute('tab_name', getQueryForm.ripos.options[tb].value);
				opt1.setAttribute('tab_name', getQueryForm.ripos.options[tb].value);
				attrList.appendChild(opt3);
				conditionAttrList.appendChild(opt1);
			}
		}
	}
}



/*************************************start of query generaton************************************************************/
function generateQuery(){
	//variable declerations
	var error=0;
	var queryObject={
		"table": tableStringMolder(getQueryForm.ripos.options, document.getElementById('data-base-list').value),
		"attribute": attributeStringMolder(getQueryForm.showMe.options),/*
		"condition": attributeStringMolder(getQueryForm.conditionAttr.options),
		"operator": querySubStringSingleSelection("operation"),
		"inputCondition": quatationValid()+document.getElementById("condition-value").value+quatationValid(),*/
		"query": function(){
				return "SELECT row_number() over() as rownum,"+this.attribute+" FROM "+this.table+" WHERE "+whereClauseString();//this.condition+" "+this.operator+" "+this.inputCondition;
			}
	};
//sub string in sql from multi selection box

		function attributeStringMolder(data_long){
			var tempString="";
			for(var index=0; index<data_long.length;index++){
				if(data_long[index].selected){
					tempString = tempString + data_long[index].getAttribute('tab_name')+"."+data_long[index].text+",";
				}
			}
				tempString = tempString+"/";
				tempString = tempString.split(",/",1);
				return tempString;
		}
	
		function tableStringMolder(data_long, db){
			var tempString="";
			for (var index=0;index<data_long.length;index++){
				if(data_long[index].selected){
					tempString = tempString + db+"."+data_long[index].value+",";
				}
			}
		tempString = tempString+"/";
		tempString = tempString.split(",/",1);
		return tempString;
		}
//sub string in sql from single selection box
	function querySubStringSingleSelection(select_id){
		var e = document.getElementById(select_id);
		return  e.options[e.selectedIndex].text;
	}
	document.getElementById("OutputQuery").value = queryObject.query();
}
	function quatationValid(windex){
		var cond = document.getElementById('condition-attr'+windex).value;
		if(cond.localeCompare('text')==0 || cond.localeCompare('character varying')==0 || cond.localeCompare('ARRAY')==0)
			return "'";
		else if(cond.localeCompare('integer')==0 || cond.localeCompare('numeric')==0)
			return "";
		else
			return "";
	}
	
	
//condition input validator
	function validateInput(){
		var cond = document.getElementById('condition-attr').value;
		var condInp = document.getElementById('condition-value');
		if(cond.localeCompare('text')==0 || cond.localeCompare('character varying')==0 || cond.localeCompare('ARRAY')==0){
			condInp.setAttribute('type', 'text');
		}
		if(cond.localeCompare('integer')==0 || cond.localeCompare('numeric')==0){
			condInp.setAttribute('type', 'number');
		}
		if(cond.localeCompare('date')==0){
			condInp.setAttribute('type', 'date');
		}
	}

	function newWhere(){
		if(!conditionAttrList.hasChildNodes()){}
		else{
			whereIndex++;
			var div0 = document.createElement('div');
			var div1 = document.createElement('div');
			var div2 = document.createElement('div');
			var div3 = document.createElement('div');
			
			div0.className = 'span11';
			div1.className = 'span4';
			div2.className = 'span3';
			div3.className = 'span4';
			
			div0.innerHTML="<select id='assign"+whereIndex+"'><option value='#'></option><option value='AND'>AND</option><option value='OR'>OR</option></select>";
			div1.innerHTML="<select name='conditionAttr"+whereIndex+"' id='condition-attr"+whereIndex+"' onchange='validateInputMore()' required></select>";
			div2.innerHTML="<select name='operation"+whereIndex+"' id='operation"+whereIndex+"' required><option value='='>=</option><option value='>'>></option><option value='<'><</option><option value='!='>!=</option><option value='<='><=</option><option value='>='>>=</option><option value='LIKE'>LIKE</option></select>";
			div3.innerHTML="<input type='text' id='condition-value"+whereIndex+"' required/>";
			
			document.getElementById("where0").appendChild(div0);
			document.getElementById("where0").appendChild(div1);
			document.getElementById("where0").appendChild(div2);
			document.getElementById("where0").appendChild(div3);
			addAttrCondition(document.getElementById('condition-attr'+whereIndex));
			}
		}
		
	function addAttrCondition(selecter){
		for (tb=0;tb<getQueryForm.ripos.options.length;tb++){
			if (getQueryForm.ripos.options[tb].selected){
				for(attrs in dbObject[this.dataBaseList.value][getQueryForm.ripos.options[tb].text]){
					var opt1 = document.createElement('option');
					opt1.value = dbObject[this.dataBaseList.value][getQueryForm.ripos.options[tb].text][attrs];
					opt1.innerHTML = attrs;
					opt1.setAttribute('tab_name', getQueryForm.ripos.options[tb].value);
					selecter.appendChild(opt1);
				}
			}
		}
	}
	function validateInputMore(){
		var cond = document.getElementById('condition-attr'+whereIndex).value;
		var condInp = document.getElementById('condition-value'+whereIndex);
		if(cond.localeCompare('text')==0 || cond.localeCompare('character varying')==0 || cond.localeCompare('ARRAY')==0){
			condInp.setAttribute('type', 'text');
		}
		if(cond.localeCompare('integer')==0 || cond.localeCompare('numeric')==0){
			condInp.setAttribute('type', 'number');
		}
		if(cond.localeCompare('date')==0){
			condInp.setAttribute('type', 'date');
		}
	}
	
	function whereClauseString(){
		var tempString="",index=0;
		do {
			if(index==0){
        		tempString+=document.getElementById('condition-attr').options[document.getElementById('condition-attr').selectedIndex].text+" "+document.getElementById('operation').options[document.getElementById('operation').selectedIndex].text+" "+quatationValid('')+document.getElementById('condition-value').value+quatationValid('');
			}
			else{
				tempString+=" "+document.getElementById('assign'+index).options[document.getElementById('assign'+index).selectedIndex].text+" "+document.getElementById('condition-attr'+index).options[document.getElementById('condition-attr'+index).selectedIndex].text+" "+document.getElementById('operation'+index).options[document.getElementById('operation'+index).selectedIndex].text+" "+quatationValid(index)+document.getElementById('condition-value'+index).value+quatationValid(index);
			}
			index++;
    }
    while (index <= whereIndex);
    return tempString;
	}
