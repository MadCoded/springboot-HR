function jobListingDeletePromptModal(itemID) {
    $('#confirm-delete form[method=POST]').attr('action', '/admin/joblistings/' + itemID+'/delete');
}