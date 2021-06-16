function Validator(formSelector){
    var _this = this;

function getParent(element, selector){
    while(element.parentElement){
        if(element.parentElement.matches(selector)){
            return element.parentElement;
        }
        element = element.parentElement;
    }
}

    var formRules = {

    };
    //quy ước tạo rules
    //- nếu có lỗi return `error message`
    //- nếu không có lỗi thì return `undefined`
    var ValidatorRules = {
        required: function(value){
            return value ? undefined : 'Vui lòng nhập thông tin!';
        },
        email: function(value){
            var regex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
            return regex.test(value) ? undefined : 'Vui lòng nhập đúng email!';
        },
        min: function(min){
            return function (value){
                return value.length >= min ? undefined : `Vui lòng nhập ít nhất ${min} kí tự!`;
            }
        },
        max: function(max){
            return function (value){
                return value.length <= max ? undefined : `Vui lòng nhập không quá ${max} kí tự!`;
            }
        },
        confirmed: function (value) {
            var password = document.querySelectorAll("[name='password']")[0].value;
            return value === password ? undefined : 'Giá trị nhập vào không chính xác';
        },
    };

    //lấy ra form trong DOM
    var formElement = document.querySelector(formSelector);

    //chỉ xử lí khi có element trong DOM
    if(formElement){
        var inputs = formElement.querySelectorAll('[name][rules]')
        for(var input of inputs){

            var rules = input.getAttribute('rules').split('|');
            for(var rule of rules){

                var ruleInfo;
                var isRuleHasValue = rule.includes(':');

                
                if(isRuleHasValue){
                    ruleInfo = rule.split(':');
                    rule = ruleInfo[0];

                    //console.log(ValidatorRules[rule](ruleInfo[1]))
                }

                var ruleFunc = ValidatorRules[rule];
                
                
                if ((isRuleHasValue)) {
                    ruleFunc = ruleFunc(ruleInfo[1]);
                }

                // console.log(rule)
                if(Array.isArray(formRules[input.name])){
                    formRules[input.name].push(ruleFunc);
                }else{
                    formRules[input.name] = [ruleFunc];
                }
            }

            //Lắng nghe sự kiện để validate (blur, change,...)
            input.onblur = handleValidate;
            input.oninput = handleClearError;
        }

        function handleValidate(event){
            var rules = formRules[event.target.name];
            var errorMessage;

            for( rule of rules ){
                errorMessage = rule(event.target.value);
                if(errorMessage){
                    break;
                }
            }

            //nếu có lỗi thì hiện thị
            if(errorMessage){
                var formGroup = getParent(event.target, '.form-group')
                if(formGroup){
                    formGroup.classList.add('invalid');

                    var formMessage = formGroup.querySelector('.form-message');
                    if(formMessage){
                        formMessage.innerText = errorMessage;
                    }
                }
            }
            return !errorMessage;
        }
        //hàm clear error message
        function handleClearError(event){
            var formGroup = getParent(event.target, '.form-group');
                formGroup.classList.add('invalid');
            if(formGroup.classList.contains('invalid')){
                formGroup.classList.remove('invalid');

                var formMessage = formGroup.querySelector('.form-message');
                if(formMessage){
                    formMessage.innerText = '';
                }
            }
        }
    }
    //Xử lí hành vi mặc định submit
    formElement.onsubmit = function(event){
        event.preventDefault();

        var inputs = formElement.querySelectorAll('[name][rules]');
        var isValid = true;
        for(var input of inputs){
            if(!handleValidate({target: input})){
                isValid = false; 
            }
        }
        console.log(isValid)
        //khi không có lỗi thì submit form
        
        if(isValid){
            if(typeof _this.onSubmit === 'function'){

                var enableInputs = formElement.querySelectorAll('[name]');
                var formValues = Array.from(enableInputs).reduce(function (values, input) {
                    switch(input.type) {
                        case 'radio':
                            values[input.name] = formElement.querySelector('input[name="' + input.name + '"]:checked').value;
                            break;
                        case 'checkbox':
                            if (!input.matches(':checked')) {
                                values[input.name] = '';
                                return values;
                            }
                            if (!Array.isArray(values[input.name])) {
                                values[input.name] = [];
                            }
                            values[input.name].push(input.value);
                            break;
                        case 'file':
                            values[input.name] = input.files;
                            break;
                        default:
                            values[input.name] = input.value;
                    }

                    return values;
                }, {});
                //gọi lại hàm onsubmit trả về giá trị của form
                _this.onSubmit(formValues);
            }else{
                formElement.submit();
            }
        }
    }
}