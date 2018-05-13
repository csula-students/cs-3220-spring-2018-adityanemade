import constants from '../constants';
import Generator from '../models/generator';

export default function (store) {
	return class GeneratorComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;

			this.rateChangeIncrement = null;
			this.calledOnce = false;
			

			this.onStateChange = this.handleStateChange.bind(this);

			// TODO: render generator initial view
			

			// TODO: subscribe to store on change event

			// TODO: add click event

		}

		handleStateChange (newState) {
			newState.generators.forEach((g) => {
				g.disableButton = newState.counter < g.unlockValue;
				document.getElementById(g.name + "_" + g.type).disabled = g.disableButton;
				document.getElementById("generator-quantity_" + g.type).innerHTML = g.quantity;
				const gModel = new Generator(g);
				if (this.buttonClicked === g.type) {
					this.buttonClicked = null;
					document.getElementById("generator-rate_" + g.type).innerHTML = gModel.generate() + "/60";
				}
				document.getElementById(g.name + "_" + g.type).innerHTML = g.unlockValue + " Resources";
			});
		}

		connectedCallback () {
			// console.log("GeneratorComponent#connectedCallback");
			this.buttonClicked = null;
			const parent = window.document.createElement('div');
			parent.className = "game-generator";

	    	this.store.state.generators.forEach((g) => {
	    		const gModel = new Generator(g);
	    		const generator = window.document.createElement('div');
	    		generator.className = g.name;

	    		const generatorPara = window.document.createElement('p');
	    		generatorPara.id = "generator-quantity_" + g.type;
	    		generatorPara.className = "generator-amount";
	    		generatorPara.innerHTML = g.quantity;
	    		generator.appendChild(generatorPara);

	    		const generatorTitle = window.document.createElement('h3');
	    		generatorTitle.innerHTML = g.title;
	    		generator.appendChild(generatorTitle);

	    		const generatorBody = window.document.createElement('p');
	    		generatorBody.innerHTML = g.description;
	    		generator.appendChild(generatorBody);

	    		const generatorButton = window.document.createElement('button');
	    		generatorButton.id = g.name + "_" + g.type;
	    		generatorButton.className = "btn-generator";
	    		generatorButton.innerHTML = g.unlockValue + " Resources";
	    		generatorButton.disabled = g.disableButton;
				generatorButton.addEventListener('click', (event) => {
					this.buttonClicked = g.type;
					this.store.dispatch({
						type: constants.actions.BUY_GENERATOR,
						generatorClicked: g.type
					});
				});
	    		generator.appendChild(generatorButton);

	    		const generatorPara2 = window.document.createElement('p');
	    		generatorPara2.id = "generator-rate_" + g.type;
	    		generatorPara2.className = "generator-amount";
	    		generatorPara2.innerHTML = gModel.generate() + "/60";
	    		generator.appendChild(generatorPara2);

	    		parent.appendChild(generator);
	    	});

			this.append(parent);
			
			this.store.subscribe(this.onStateChange);
		}

		disconnectedCallback () {
			// console.log("GeneratorComponent#disconnectedCallback");
			this.store.unsubscribe(this.onStateChange);
		}
	};
}
