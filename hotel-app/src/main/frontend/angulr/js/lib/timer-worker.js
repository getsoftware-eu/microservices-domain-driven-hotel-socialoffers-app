var intervalIds = {};

self.onmessage = function(e) {
    switch (e.data.command) {
        case 'interval:start':
            var intvalId = setInterval(function() {
                postMessage({
                    message: 'interval:tick',
                    id: e.data.id
                });
            }, e.data.interval);

            postMessage({
                message: 'interval:started',
                id: e.data.id
            });

            intervalIds[e.data.id] = intvalId;
            break;

        case 'interval:clear':
            clearInterval(intervalIds[e.data.id]);

            postMessage({
                message: 'interval:cleared',
                id: e.data.id
            });

            delete intervalIds[e.data.id];
            break;
        
        //intervals are never deleted, so remobe all!
        case 'interval:clearAll':
            
            var keys = [];
            
            for (var key in intervalIds) {
                if (intervalIds.hasOwnProperty(key)) {
                    keys.push(key);
                }
            }
            
            for(var k=0; k<keys.length; k++)
            {
                var nextClientIntervalId = keys[k];
                clearInterval(intervalIds[nextClientIntervalId]);
                delete intervalIds[nextClientIntervalId];
            }
            
            break;
    }
};