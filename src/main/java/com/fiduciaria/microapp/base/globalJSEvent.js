Wicket.Event.subscribe('/ajax/call/failure',
        function (jqEvent, attributes, jqXHR, errorThrown, textStatus) {

            if (!errorThrown) {
                
            } else {
                
                window.location.reload();
                
            }

        }
);

