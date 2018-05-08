var rnavs = [];
var navs = [{
	"title": "个人档案",
	"icon": "&#xe612;",
	"spread": true,
	"children": [{
	    "lay-id":"0",
		"title": "员工信息",
		"icon": "&#xe612;",
		"href": "personal-profile/user-info"
	}, {
		"title": "员工档案",
		"icon": "&#xe63c;",
		"href": "personal-profile/profile-info"
	}, {
		"title": "员工合同",
		"icon": "&#xe63c;",
		"href": "personal-profile/contract-info"
	}]
}, {
	"title": "教育培训",
	"icon": "&#xe60a;",
	"spread": false,
	"children": [{
		"title": "类别维护",
		"icon": "fa-table",
		"href": "/training/training"
	}, {
		"title": "培训记录",
		"icon": "fa-navicon",
		"href": "/training/training-record"
	}]
},{
    "title": "部门管理",
    "icon": "&#xe630;",
    "spread": false,
    "children": [{
        "title": "部门管理",
        "icon": "fa-table",
        "href": "dept-mana/department-info"
    }, {
        "title": "职务管理",
        "icon": "fa-navicon",
        "href": "dept-mana/position"
    }]
},{
    "title": "人事调配",
    "icon": "fa-cogs",
    "spread": false,
    "children": [{
        "title": "人事调动",
        "icon": "fa-table",
        "href": "/personnel-deployment/transfer"
    }, {
        "title": "新进员工",
        "icon": "fa-navicon",
        "href": "/personnel-deployment/sprog"
    }]
},{
    "title": "系统管理",
    "icon": "&#xe631;",
    "spread": false,
    "children": [{
        "title": "用户管理",
        "icon": "fa-table",
        "href": "/sys-manager/user-mana"
    }, {
        "title": "角色管理",
        "icon": "&#xe620;",
        "href": "/sys-manager/role-mana"
    }]
}];