class PubSub {
        constructor () {
            this.subscribers = [];
        }

        // subscribe allows a new subscriber to listen for changes by providing
        // callback function in the parameter
        subscribe (fn) {
            this.subscribers.push(fn);
        }

        // one can publish any data to subscribers
        publish (data) {
            this.subscribers.forEach(subscriber => {
                subscriber(data);
            });
        }
    }


const pubSub = new PubSub();
// var count = 0;
const colors = ['--red', '--blue', '--green'];
var index = -1;

const h2_counter = document.querySelector('#resource-counter');
pubSub.subscribe(action => {
    if (action.type !== 'INC_COUNT') {
        return;
    }
    // count++;
    // window.incrementalGame.state.counter = count;
    window.incrementalGame.state.counter++;
    h2_counter.textContent = window.incrementalGame.state.counter;
    h2_counter.innerHTML = window.incrementalGame.state.counter;
    // console.log(window.incrementalGame.state.counter);

    const oldColor = getColor(colors, index);
    if (oldColor) {
        h2_counter.classList.remove(oldColor);
    }
    index ++;
    index = index % colors.length;
    window.incrementalGame.state.index = index;
    const newColor = getColor(colors, index);
    h2_counter.classList.add(newColor);

});


const btn_res = document.querySelector('#btn-res');
btn_res.addEventListener('click', function() {
    pubSub.publish({
        type: 'INC_COUNT',
        payload: window.incrementalGame.state.counter,
        color: window.incrementalGame.state.index  
    });
});

function getColor (colors, index) {
    if (index < 0) {
        return '';
    }
    return colors[index];
}