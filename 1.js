var orgTree = $('#orgTree');
var $searchOrg = null;

//====================================================组织机构树===============================================//
orgTree.jstree({
    'core' : {
        'data' : {
            'url' : '#(ctx)/Organization/getOrgTree?multiLevel=true',
            'data' : function(node) {
                return {
                    'id' : node.id
                }
            }
        }
    },
    'types' : {
        'default' : {
            'icon' : 'glyphioon glyphicon-home'
        }
    },
//    'plugins':['types','sort']
});
orgTree.on('changed.jstree', function(e, data) {
    $('#orgName').val(data.node.text);
    orgTree.jstree('close_all');
    $searchOrg = data.node.id;
    //      alert(" $searchOrg:"+ $searchOrg);
});
$(".search").click(function() {
    $("[name='p']").val("1");
    Search();
});
function formatRepo(repo) {
    if (repo.loading)
        return repo.text;
    var markup = repo.name;
    return markup;
}

function formatRepoSelection(repo) {
    return repo.name || repo.text;
}