function isInViewport(element) {
    const rect = element.getBoundingClientRect();
    const html = document.documentElement;
    return rect.top >= 0 &&
            rect.left >= 0 &&
            rect.bottom <= (window.innerHeight || html.clientHeight) &&
            rect.right <= (window.innerWidth || html.clientWidth);
}

class Popover {

    constructor(trigger, { position = 'top', className = 'pf-m',
            bodyPopover = '', headerPopover = '', footerPopover = '' }) {
        this.trigger = trigger;
        this.position = position;
        this.className = className;
        this.orderedPositions = ['top', 'right', 'bottom', 'left'];

        // const popoverTemplate = document.querySelector(`[data-popover=${trigger.dataset.popoverTarget}]`);
        this.popover = document.createElement('div');

        this.popover.classList.add("pf-c-popover", "pf-m-right");

        this.popover.innerHTML = `
            <div class="pf-c-popover__arrow"></div>
                <div class="pf-c-popover__content">
                <button class="pf-c-button pf-m-plain" type="button" aria-label="Close">
                <i class="fas fa-times" aria-hidden="true"></i>
            </button>
            <h1 class="pf-c-title pf-m-md" >${headerPopover}</h1>
            <div class="pf-c-popover__body">${bodyPopover}</div>
            <footer class="pf-c-popover__footer">${footerPopover}</footer>
            </div>
        
        `;

        Object.assign(this.popover.style, {
            position: 'fixed', zIndex: '99999'
        });

        this.popover.classList.add(className);

        this.handleWindowEvent = () => {
            if (this.isVisible) {
                this.show();
            }
        };

        this.handleDocumentEvent = (evt) => {
            if (this.isVisible && evt.target !== this.trigger && evt.target !== this.popover) {
                this.popover.remove();
            }
        };
    }

    get isVisible() {
        return document.body.contains(this.popover);
    }

    show() {

        document.addEventListener('click', this.handleDocumentEvent);

        if (document.querySelector('.pf-c-page__main') !== null) {

            document.querySelector('.pf-c-page__main').addEventListener('scroll', this.handleWindowEvent);

        }



        window.addEventListener('scroll', this.handleWindowEvent);
        window.addEventListener('resize', this.handleWindowEvent);

        document.body.appendChild(this.popover);

        const {top: triggerTop, left: triggerLeft} = this.trigger.getBoundingClientRect();
        const {offsetHeight: triggerHeight, offsetWidth: triggerWidth} = this.trigger;
        const {offsetHeight: popoverHeight, offsetWidth: popoverWidth} = this.popover;

        const positionIndex = this.orderedPositions.indexOf(this.position);

        const positions = {
            top: {
                name: 'top',
                top: triggerTop - popoverHeight - 20,
                left: triggerLeft - ((popoverWidth - triggerWidth) / 2)
            },
            right: {
                name: 'right',
                top: triggerTop - ((popoverHeight - triggerHeight) / 2),
                left: triggerLeft + triggerWidth + 30
            },
            bottom: {
                name: 'bottom',
                top: triggerTop + triggerHeight + 20,
                left: triggerLeft - ((popoverWidth - triggerWidth) / 2)
            },
            left: {
                name: 'left',
                top: triggerTop - ((popoverHeight - triggerHeight) / 2),
                left: triggerLeft - popoverWidth - 30
            }
        };

        const position = this.orderedPositions
                .slice(positionIndex)
                .concat(this.orderedPositions.slice(0, positionIndex))
                .map(pos => positions[pos])
                .find(pos => {
                    this.popover.style.top = `${pos.top}px`;
                    this.popover.style.left = `${pos.left}px`;
                    return isInViewport(this.popover);
                });

        this.orderedPositions.forEach(pos => {
            this.popover.classList.remove(`${this.className}-${pos}`);
        });

        if (position) {
            this.popover.classList.add(`${this.className}-${position.name}`);
        } else {
            this.popover.style.top = positions.bottom.top;
            this.popover.style.left = positions.bottom.left;
            this.popover.classList.add(`${this.className}-bottom`);
        }
    }

    destroy() {
        this.popover.remove();

        document.removeEventListener('click', this.handleDocumentEvent);
        window.removeEventListener('scroll', this.handleWindowEvent);
        window.removeEventListener('resize', this.handleWindowEvent);

        if (document.querySelector('.pf-c-page__main') !== null) {

            document.querySelector('.pf-c-page__main').removeEventListener('scroll', this.handleWindowEvent);

        }

    }

    toggle() {
        if (this.isVisible) {
            this.destroy();
        } else {
            this.show();
        }
    }

}


/*const position = document.getElementById('position');
 const trigger = document.getElementById('trigger');
 let popover = new Popover(trigger, { position: 'top' });
 
 position.addEventListener('change', () => {
 popover.destroy();
 popover = new Popover(trigger, { position: position.value });
 });
 
 trigger.addEventListener('click', () => popover.toggle());
 */

/* Wicket.Event.subscribe('/dom/node/removing',
 function(jqEvent, attributes, jqXHR, errorThrown, textStatus) {
 var componentId = '#' + attributes['id'];
 if($(componentId).datepicker !== undefined)
 destroy();
 }
 );*/
