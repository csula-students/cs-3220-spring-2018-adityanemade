import '@webcomponents/webcomponentsjs';

import {loop} from './game';
import Store from './store';
import reducer from './reducer';

import ButtonComponent from './views/button';
import CounterComponent from './views/counter';
import ExampleComponent from './views/example';
import GeneratorComponent from './views/generator';
import StoryBookComponent from './views/story-book';
import constants from './constants';

/**
 * Data flow diagram
 +----------------------------------------------------+
 | +------------------+          +------------------+ |
 | |                  |          |                  | |
++-|       Loop       |<---------|    Generator     | |
|| |                  |          |                  | |
|| +------------------+          +------------------+ |
||G          ^                                        |
||a          +-----------------------------+          |
||m                                        |          |
||e                                        |          |
||                               +------------------+ |
||                               |                  | |
||                               |     Stories      | |
||                               |                  | |
||                               +------------------+ |
|+----------------------------------------------------+
+------------------------------------------------------------+
|                                                            |
|                                                            |
|                                                            |
|                                                            |
|                                                            |
|                                                            |
|                                                            |
|                                                            |
|       +----------------------------------------------------+----------+
|       | +------------------+                     +------------------+ |
|       | |                  |        Mutates      |                  | |
|       | |     Reducer      |-------------------->|      State       | |
|       | |                  |                     |                  | |
|       | +------------------+                     +------------------+ |
|       |S          ^                                        |          |
|       |t          |                                        |          |
|       |o          |                                        |          |
|       |r          | Triggers                     Notifies  |          |
|       |e          |                                        |          |
|       |           |                                        v          |
|       | +------------------+                     +------------------+ |
|       | |                  |                     |                  | |
+-------+>|      Action      |                     |    Listeners     | |
        | |                  |                     |                  | |
        | +------------------+                     +------------------+ |
        +-----------^----------------------------------------+----------+
                    |                                        |
                    |                                        |
                    |                                        |
                    |                                        |
                    | Dispatches                             |
                    |                                        |
                    |                                        |
          +------------------+                               |
          |                  |                               |
          |      Views       |              Notifies changes |
          |    Components    |<------------------------------+
          |                  |
          +------------------+
 */
main();

// main function wraps everything at top level
function main () {
	// TODO: fill the blank based on the theme you have choosen
	const initialState = {
		example: 'Hello custom element',
		counter: 0,
		generators: [
			{
				type: 0,
				name: 'generator-1',
				title: 'Punch',
				description: '1 punch generates 5 hits per second',
				rate: 5,
				quantity: 0,
				baseCost: 10,
				unlockValue: 10,
				disableButton: true
			},
			{
				type: 1,
				name: 'generator-2',
				title: 'Jab',
				description: '1 jab generates 10 hits per second',
				rate: 10,
				quantity: 0,
				baseCost: 100,
				unlockValue: 100,
				disableButton: true
			},
			{
				type: 2,
				name: 'generator-3',
				title: 'Kick',
				description: '1 kick 20 hits per second',
				rate: 20,
				quantity: 0,
				baseCost: 1000,
				unlockValue: 1000,
				disableButton: true
			}
		],
		story: [{
			name: 'story-1',
			description: 'Oh that is going to hurt',
			triggeredAt: 10,
			state: 'hidden'
		},{
			name: 'story-2',
			description: 'That face seems to take it pretty well',
			triggeredAt: 100,
			state: 'hidden'
		},{
			name: 'story-3',
			description: 'We have a knockdown',
			triggeredAt: 1000,
			state: 'hidden'
		}]
	};

	// initialize store
	const store = new Store(reducer, initialState);
	// console.log(ExampleComponent(store));

	// define web components
	window.customElements.define('component-example', ExampleComponent(store));
	// no longer used
	window.customElements.define('game-button', ButtonComponent(store));
	window.customElements.define('game-counter', CounterComponent(store));
	// lab 3
	window.customElements.define('game-generator', GeneratorComponent(store));
	// homework 1
	window.customElements.define('game-story-book', StoryBookComponent(store));

	// For ease of debugging purpose, we will expose the critical store under window
	// ps: window is global
	window.store = store;

	window.globalGeneratorRate = 0;

	// start game loop
	loop(store);
}
