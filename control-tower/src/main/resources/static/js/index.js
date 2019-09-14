const homeContainer = document.querySelector('.home-container');
const scrapingContainer = document.querySelector('.scraping-container');
const mergingContainer = document.querySelector('.merging-container');
const emailsContainer = document.querySelector('.emails-container');
const calculatingContainer = document.querySelector('.calculating-container');
const helpContainer = document.querySelector('.help-container');
const contactsContainer = document.querySelector('.contacts-container');

const scrapingSiteContainer = document.querySelector('.scraping-container .site-container');
const scrapingAddSiteButton = document.querySelector('.scraping-container .site-add');

const scrapingSiteTypes = document.querySelectorAll('.scraping-container .email-file-type');

const scrapingEmailSendButton = document.querySelector('.scraping-container .email-send-button');

function onHash() {
    switch (window.location.hash) {
        case '#home':
            homeContainer.classList.remove('display-none');
            scrapingContainer.classList.add('display-none');
            mergingContainer.classList.add('display-none');
            emailsContainer.classList.add('display-none');
            calculatingContainer.classList.add('display-none');
            helpContainer.classList.add('display-none');
            contactsContainer.classList.add('display-none');
            break;
        case '#scraping':
            homeContainer.classList.add('display-none');
            scrapingContainer.classList.remove('display-none');
            mergingContainer.classList.add('display-none');
            emailsContainer.classList.add('display-none');
            calculatingContainer.classList.add('display-none');
            helpContainer.classList.add('display-none');
            contactsContainer.classList.add('display-none');
            break;
        case '#merging':
            homeContainer.classList.add('display-none');
            scrapingContainer.classList.add('display-none');
            mergingContainer.classList.remove('display-none');
            emailsContainer.classList.add('display-none');
            calculatingContainer.classList.add('display-none');
            helpContainer.classList.add('display-none');
            contactsContainer.classList.add('display-none');
            break;
        case '#emails':
            homeContainer.classList.add('display-none');
            scrapingContainer.classList.add('display-none');
            mergingContainer.classList.add('display-none');
            emailsContainer.classList.remove('display-none');
            calculatingContainer.classList.add('display-none');
            helpContainer.classList.add('display-none');
            contactsContainer.classList.add('display-none');
            break;
        case '#calculating':
            homeContainer.classList.add('display-none');
            scrapingContainer.classList.add('display-none');
            mergingContainer.classList.add('display-none');
            emailsContainer.classList.add('display-none');
            calculatingContainer.classList.remove('display-none');
            helpContainer.classList.add('display-none');
            contactsContainer.classList.add('display-none');
            break;
        case '#help':
            homeContainer.classList.add('display-none');
            scrapingContainer.classList.add('display-none');
            mergingContainer.classList.add('display-none');
            emailsContainer.classList.add('display-none');
            calculatingContainer.classList.add('display-none');
            helpContainer.classList.remove('display-none');
            contactsContainer.classList.add('display-none');
            break;
        case '#contacts':
            homeContainer.classList.add('display-none');
            scrapingContainer.classList.add('display-none');
            mergingContainer.classList.add('display-none');
            emailsContainer.classList.add('display-none');
            calculatingContainer.classList.add('display-none');
            helpContainer.classList.add('display-none');
            contactsContainer.classList.remove('display-none');
            break;
        default:
    }
}

function initPage() {
    window.onhashchange = onHash;
    window.location.hash = '#home';

    scrapingAddSiteButton.addEventListener('click', function () {
        const newSiteItem = document.querySelector('.scraping-container template').content.querySelector('.site-item').cloneNode(true);

        const siteDeleteButton = newSiteItem.querySelector('.site-delete-button');
        siteDeleteButton.addEventListener('click', function () {
            newSiteItem.remove();
        });

        const siteTagsContainer = newSiteItem.querySelector('.site-tags-container');
        const siteTagAll = siteTagsContainer.querySelector('.site-tag-all');

        const siteSelect = newSiteItem.querySelector('.site-select');
        siteSelect.addEventListener('change', function () {
            while (siteTagsContainer.childElementCount > 1) {
                siteTagsContainer.removeChild(siteTagsContainer.firstChild);
            }
            tagAll.classList.remove('tag-selected');
            modelData.outputSites.find(function (item) {
                return item.id === +siteSelect.value;
            }).category.forEach(function (item) {
                const newTag = document.querySelector('.scraping-container template').content.querySelector('.site-tag').cloneNode(true);
                newTag.textContent = item;
                newTag.addEventListener('click', function () {
                    newTag.classList.toggle('tag-selected');
                    if (siteTagsContainer.querySelectorAll('.site-tag').length === siteTagsContainer.querySelectorAll('.site-tag.tag-selected').length) {
                        tagAll.classList.add('tag-selected');
                    } else {
                        tagAll.classList.remove('tag-selected');
                    }
                });
                siteTagsContainer.insertBefore(newTag, siteTagAll);
            });
        });

        modelData.outputSites[0].category.forEach(function (item) {
            const newTag = document.querySelector('.scraping-container template').content.querySelector('.site-tag').cloneNode(true);
            newTag.textContent = item;
            newTag.addEventListener('click', function () {
                newTag.classList.toggle('tag-selected');
                if (siteTagsContainer.querySelectorAll('.site-tag').length === siteTagsContainer.querySelectorAll('.site-tag.tag-selected').length) {
                    tagAll.classList.add('tag-selected');
                } else {
                    tagAll.classList.remove('tag-selected');
                }
            });
            siteTagsContainer.insertBefore(newTag, siteTagAll);
        });

        const tagAll = siteTagsContainer.querySelector('.site-tag-all');
        tagAll.addEventListener('click', function () {
            if (tagAll.classList.contains('tag-selected')) {
                [].forEach.call(siteTagsContainer.children, function (item) {
                    item.classList.remove('tag-selected');
                });
            } else {
                [].forEach.call(siteTagsContainer.children, function (item) {
                    item.classList.add('tag-selected');
                });
            }
        });

        scrapingSiteContainer.appendChild(newSiteItem);
    });

    [].forEach.call(scrapingSiteTypes, function (item) {
        item.addEventListener('click', function () {
            item.classList.toggle('file-type-selected');
        });
    });

    scrapingEmailSendButton.addEventListener('click',function () {
        const selectedSiteIds = [].map.call(scrapingSiteContainer.children,function (item) {
            return +item.querySelector('.site-select').value;
        });
        const selectedSitesObjs = modelData.outputSites.filter(function (item) {
            return selectedSiteIds.includes(item.id);
        });
        for (let i = 0; i < selectedSitesObjs.length; i++) {
            const selectedTags = scrapingSiteContainer.children[i].querySelectorAll('.site-tag.tag-selected');
            selectedSitesObjs[i].category = selectedSitesObjs[i].category.filter(function (item) {
               return true;
            });
        }


        const outputData = Object.assign({}, modelData);
        outputData.outputSites = selectedSitesObjs;
    });
}

initPage();